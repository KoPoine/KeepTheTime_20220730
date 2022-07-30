package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.FriendData

class FriendRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<FriendData>
        ): RecyclerView.Adapter<FriendRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : FriendData) {
            val profileImg = itemView.findViewById<ImageView>(R.id.profileImg)
            val nickTxt = itemView.findViewById<TextView>(R.id.nickTxt)

            nickTxt.text = item.nick_name
            Glide.with(mContext).load(item.profile_img).into(profileImg)
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