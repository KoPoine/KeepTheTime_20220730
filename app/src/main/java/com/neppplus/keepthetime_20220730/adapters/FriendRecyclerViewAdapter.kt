package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.FriendData

class FriendRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<FriendData>
        ): RecyclerView.Adapter<FriendRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : FriendData) {

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