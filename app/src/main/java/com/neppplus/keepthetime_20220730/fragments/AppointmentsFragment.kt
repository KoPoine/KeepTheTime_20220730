package com.neppplus.keepthetime_20220730.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_20220730.EditAppointmentActivity
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.adapters.AppointmentRecyclerViewAdapter
import com.neppplus.keepthetime_20220730.databinding.FragmentAppointmentsBinding
import com.neppplus.keepthetime_20220730.datas.AppointmentData
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import org.json.JSONObject
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

    override fun onResume() {
        super.onResume()
        getDataFromServer()
    }

    override fun setupEvents() {
        mBinding.addAppointmentBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        mAppointAdapter = AppointmentRecyclerViewAdapter(mContext, mList)
        mBinding.appointmentsRecyclerView.adapter = mAppointAdapter
        mBinding.appointmentsRecyclerView.layoutManager = LinearLayoutManager(mContext)

        getDataFromServer()
    }

    fun getDataFromServer () {

        Log.d("진입", "리스트")

        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    mList.clear()
                    mList.addAll(br.data.appointments)

                    Log.d("약속", br.data.appointments.toString())

                    mAppointAdapter.notifyDataSetChanged()
                }
                else {
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val code = jsonObj.getInt("code")
                    val message = jsonObj.getString("message")

                    Log.e("List", "code : $code, message : $message")
                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.e("List", t.toString())
            }
        })
    }

}