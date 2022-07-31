package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.SearchFriendActivity
import com.neppplus.keepthetime_20220730.api.APIList
import com.neppplus.keepthetime_20220730.api.ServerApi
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.UserData
import com.neppplus.keepthetime_20220730.utils.AppUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFriendRecyclerViewAdapter(
    val mContext: Context,
    val mList : ArrayList<UserData>
) : RecyclerView.Adapter<SearchFriendRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : UserData) {

            val apiList = ServerApi.getRetrofit(mContext).create(APIList::class.java)

            val profileImg = itemView.findViewById<ImageView>(R.id.profileImg)
            val nickTxt = itemView.findViewById<TextView>(R.id.nickTxt)
            val addFriendBtn = itemView.findViewById<Button>(R.id.addFriendBtn)

            Glide.with(mContext).load(item.profile_img).into(profileImg)
            nickTxt.text = item.nick_name
            addFriendBtn.setOnClickListener {
//                친구 요청하기 API 통신 진행
                apiList.getRequestAddFriend(item.id).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(mContext, "${item.nick_name}님께 친구 요청을 보냈습니다.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val message = AppUtil.getMessageFromErrorBody(response)
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.search_friend_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}