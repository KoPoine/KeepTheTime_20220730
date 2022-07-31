package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.UserData

class SearchFriendRecyclerViewAdapter(
    val mContext: Context,
    val mList : ArrayList<UserData>
) : RecyclerView.Adapter<SearchFriendRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : UserData) {
            val profileImg = itemView.findViewById<ImageView>(R.id.profileImg)
            val nickTxt = itemView.findViewById<TextView>(R.id.nickTxt)
            val addFriendBtn = itemView.findViewById<Button>(R.id.addFriendBtn)

            Glide.with(mContext).load(item.profile_img).into(profileImg)
            nickTxt.text = item.nick_name
            addFriendBtn.setOnClickListener {
//                친구 요청하기 API 통신 진행
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