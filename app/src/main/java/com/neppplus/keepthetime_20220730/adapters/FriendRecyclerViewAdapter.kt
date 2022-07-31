package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.FriendListActivity
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.api.APIList
import com.neppplus.keepthetime_20220730.api.ServerApi
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.FriendData
import com.neppplus.keepthetime_20220730.fragments.InviteFriendsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<FriendData>,
    val type : String
        ): RecyclerView.Adapter<FriendRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : FriendData) {

            val apiList = ServerApi.getRetrofit(mContext).create(APIList::class.java)

            val profileImg = itemView.findViewById<ImageView>(R.id.profileImg)
            val nickTxt = itemView.findViewById<TextView>(R.id.nickTxt)
            val buttonLayout = itemView.findViewById<LinearLayout>(R.id.requestLayout)
            val acceptBtn = itemView.findViewById<Button>(R.id.acceptBtn)
            val deniedBtn = itemView.findViewById<Button>(R.id.deniedBtn)

            nickTxt.text = item.nick_name
            Glide.with(mContext).load(item.profile_img).into(profileImg)

            if (type == "requested") {
                buttonLayout.visibility = View.VISIBLE

//                fun answerRequest (answer : String) {
//                    //                요청 들어온 친구 목록에 대한 확인 / 취소 버튼 Event 실행
//                    apiList.getRequestBooleanRequested(
//                        item.id, answer
//                    ).enqueue(object : Callback<BasicResponse>{
//                        override fun onResponse(
//                            call: Call<BasicResponse>,
//                            response: Response<BasicResponse>
//                        ) {
//                            if (response.isSuccessful) {
//                                if (answer == "수락") {
//                                    Toast.makeText(mContext, "요청을 수락하였습니다.", Toast.LENGTH_SHORT).show()
//                                }
//                                else {
//                                    Toast.makeText(mContext, "요청을 거절하였습니다.", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//
//                        }
//                    })
//                }
//
//                acceptBtn.setOnClickListener {
//                    answerRequest("수락")
//                }
//
//                deniedBtn.setOnClickListener {
//                    answerRequest("거절")
//                }

//                어떠한 "상황"에 따른 응답을 별도 처리
                val ocl = object : View.OnClickListener{
                    override fun onClick(p0: View?) {
                        val answer = p0!!.tag.toString()
                        apiList.getRequestBooleanRequested(
                            item.id, answer
                        ).enqueue(object : Callback<BasicResponse>{
                            override fun onResponse(
                                call: Call<BasicResponse>,
                                response: Response<BasicResponse>
                            ) {
                                if (response.isSuccessful) {
                                    if (answer == "수락") {
                                        Toast.makeText(mContext, "요청을 수락하였습니다.", Toast.LENGTH_SHORT).show()
                                    }
                                    else {
                                        Toast.makeText(mContext, "요청을 거절하였습니다.", Toast.LENGTH_SHORT).show()
                                    }

//                                    ((mContext as FriendListActivity)
//                                        // Activity에서 Fragment를 관리하는 supportFragmentManager 호출
//                                        .supportFragmentManager
//                                            // supportFragmentManager에서 실제 Fragment를 TAG를 통해서 찾아오는 코드
//                                            // Fragment의 경우 f + 몇 번째 프래그먼트인지를 통해서 tag가 자동 생성
//                                        .findFragmentByTag("f1") as InviteFriendsFragment)
//                                        .getFriendListFromServer()
                                }
                            }

                            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                            }
                        })

                    }
                }

                acceptBtn.setOnClickListener(ocl)
                deniedBtn.setOnClickListener(ocl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}