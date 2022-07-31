package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.AppointmentData

class AppointmentRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<AppointmentData>
        ) : RecyclerView.Adapter<AppointmentRecyclerViewAdapter.AppointmentViewHolder>(){

    inner class AppointmentViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : AppointmentData) {
            val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
            val dateTimeTxt = itemView.findViewById<TextView>(R.id.dateTimeTxt)
            val placeTxt = itemView.findViewById<TextView>(R.id.placeTxt)
            val memberTxt = itemView.findViewById<TextView>(R.id.memberTxt)

            titleTxt.text = item.title
            placeTxt.text = "약속 장소 : ${item.place}"
            memberTxt.text = "참여 인원 : ${item.invited_friends.size}명"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.appoinment_list_item, parent, false)
        return AppointmentViewHolder(row)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}