package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_20220730.EditMyPlaceActivity
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.datas.PlaceData

class MyPlaceRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<PlaceData>
        ) : RecyclerView.Adapter<MyPlaceRecyclerViewAdapter.MyPlaceViewHolder>() {

    inner class MyPlaceViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind (item : PlaceData) {
            val nameTxt = itemView.findViewById<TextView>(R.id.placeNameTxt)
            val primeTxt = itemView.findViewById<TextView>(R.id.primeTxt)

            nameTxt.text = item.name
            if (item.is_primary) {
                primeTxt.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                val myIntent = Intent(mContext, EditMyPlaceActivity::class.java)
                myIntent.putExtra("placeData", item)
                mContext.startActivity(myIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlaceViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.place_list_item, parent, false)
        return MyPlaceViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyPlaceViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}