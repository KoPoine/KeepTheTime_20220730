package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.FriendData
import com.neppplus.keepthetime_20220730.datas.UserData

class FriendSpinnerAdapter(
    val mContext : Context,
    val resId : Int,
    val mList: ArrayList<FriendData>
) : ArrayAdapter<FriendData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(resId, null)
        }
        val row = tempRow!!

        val profileImg = row.findViewById<ImageView>(R.id.profileImg)
        val nickTxt = row.findViewById<TextView>(R.id.nickTxt)

        val data = mList[position]

        Glide.with(mContext).load(data.profile_img).into(profileImg)
        nickTxt.text = data.nick_name

        return row
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}