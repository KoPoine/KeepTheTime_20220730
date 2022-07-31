package com.neppplus.keepthetime_20220730

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_20220730.adapters.MyPlaceRecyclerViewAdapter
import com.neppplus.keepthetime_20220730.databinding.ActivityMyPlaceBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.PlaceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPlaceActivity : BaseActivity() {

    lateinit var mBinding : ActivityMyPlaceBinding

    lateinit var mPlaceAdapter : MyPlaceRecyclerViewAdapter
    val mList = ArrayList<PlaceData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_place)
        setupEvents()
        setValues()
        Log.d("MyPlaceActivity", "onCreate")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyPlaceActivity", "onPause")
    }

    override fun onResume() {
        super.onResume()
        getMyPlaceFromServer()
    }

    override fun setupEvents() {
        addBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditMyPlaceActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        titleTxt.text = "내 출발 장소 관리"
        addBtn.visibility = View.VISIBLE
        backBtn.visibility = View.VISIBLE

        mPlaceAdapter = MyPlaceRecyclerViewAdapter(mContext, mList)
        mBinding.myPlaceRecyclerview.adapter = mPlaceAdapter
        mBinding.myPlaceRecyclerview.layoutManager = LinearLayoutManager(mContext)
    }

    fun getMyPlaceFromServer () {
        apiList.getRequestMyPlace().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    mList.clear()
                    mList.addAll(br.data.places)

                    mPlaceAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}