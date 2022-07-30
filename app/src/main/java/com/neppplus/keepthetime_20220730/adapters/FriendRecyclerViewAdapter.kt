package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.databinding.FriendListItemBinding
import com.neppplus.keepthetime_20220730.datas.FriendData

class FriendRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<FriendData>
        ): RecyclerView.Adapter<FriendRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder (val binding : FriendListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (item : FriendData) {

            binding.nickTxt.text = item.nick_name
            Glide.with(mContext).load(item.profile_img).into(binding.profileImg)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(FriendListItemBinding.inflate(LayoutInflater.from(mContext), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}