package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_20220730.adapters.SearchFriendRecyclerViewAdapter
import com.neppplus.keepthetime_20220730.databinding.ActivitySearchFriendBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.UserData
import com.neppplus.keepthetime_20220730.utils.AppUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFriendActivity : BaseActivity() {

    lateinit var mBinding : ActivitySearchFriendBinding

    lateinit var mAdapter : SearchFriendRecyclerViewAdapter
    val mList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        mBinding.searchBtn.setOnClickListener {
//            0. 기존의 친구 검색 목록을 제거
            mList.clear()

//            1. 서버에서 친구 목록을 검색해서 받아오기
            val inputNick = mBinding.inputEdt.text.toString()
            apiList.getRequestSearchUser(inputNick).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!
//                        2. 친구 목록을 정리(우리의 리스트에 반영)
                        mList.addAll(br.data.users)

//                        3. 어댑터에 연결(notifyDataSetChanged)
                        mAdapter.notifyDataSetChanged()

//                        [도전과제] 검색이 없을때 아래쪽에 "검색된 결과가 없습니다." 텍스트 출력
//                        [도전과제2] 검색 버튼을 눌렀을때, 가상 키보드 사라지게(구글링해서 작성)
                    }
                    else {
                        AppUtil.getMessageFromErrorBody(response, mContext)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })

        }
    }

    override fun setValues() {
        titleTxt.text = "친구 목록 검색"
        backBtn.visibility = View.VISIBLE

        mAdapter = SearchFriendRecyclerViewAdapter(mContext, mList)
        mBinding.friendsRecyclerView.adapter = mAdapter
        mBinding.friendsRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}