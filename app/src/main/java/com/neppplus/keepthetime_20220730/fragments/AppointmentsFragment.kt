package com.neppplus.keepthetime_20220730.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.adapters.AppointmentRecyclerViewAdapter
import com.neppplus.keepthetime_20220730.databinding.FragmentAppointmentsBinding
import com.neppplus.keepthetime_20220730.datas.AppointmentData
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentsFragment : BaseFragment() {

    lateinit var mBinding : FragmentAppointmentsBinding

    lateinit var mAppointAdapter : AppointmentRecyclerViewAdapter
    val mList = ArrayList<AppointmentData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointments, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mAppointAdapter = AppointmentRecyclerViewAdapter(mContext, mList)
        mBinding.appointmentsRecyclerView.adapter = mAppointAdapter
        mBinding.appointmentsRecyclerView.layoutManager = LinearLayoutManager(mContext)

        getDataFromServer()
    }

    fun getDataFromServer () {
        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    mList.clear()
                    mList.addAll(br.data.appointments)

                    mAppointAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

}