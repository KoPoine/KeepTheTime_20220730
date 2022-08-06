package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.PlaceData

class StartPlaceSpinnerAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<PlaceData>
) : ArrayAdapter<PlaceData>(mContext, resId, mList) {

//    선택된 아이템을 보여주는 함수 > getView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(resId, null)
        }
        val row = tempRow!!

        val placeNameTxt = row.findViewById<TextView>(R.id.placeNameTxt)
        val primeTxt = row.findViewById<TextView>(R.id.primeTxt)

        val data = mList[position]

        placeNameTxt.text = data.name

        if (data.is_primary) {
            primeTxt.visibility = View.VISIBLE
        }

        return row
    }

//    Dropdown 버튼을 눌렀을때, 리스트에 표시되는 한 칸에 view를 나타내는 함수 > getDropDownView

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}