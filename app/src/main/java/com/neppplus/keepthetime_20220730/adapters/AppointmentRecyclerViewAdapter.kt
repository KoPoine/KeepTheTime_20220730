package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.AppointmentData
import java.text.SimpleDateFormat

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

            Log.d("약속", item.toString())

            titleTxt.text = item.title
//            dateTimeTxt.text = "약속 시간 : ${item.datetime}"  // yyyy-MM-dd HH:mm:ss 형태로 내려받은 String을 변환

//            우리 서버가 내려주는 String 양식을 simpleDateFormat 저장
            val stringToDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

//            String을 Date양식으로 변환(Parse)
            val datetime = stringToDate.parse(item.datetime)

//            우리가 표출을 원하는 양식을 작성 (월/일 오전 시:분)
            val sdf = SimpleDateFormat("M/d a h:mm")

            dateTimeTxt.text = "약속 시간 : ${sdf.format(datetime)}"

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