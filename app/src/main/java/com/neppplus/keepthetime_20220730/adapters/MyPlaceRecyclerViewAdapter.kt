package com.neppplus.keepthetime_20220730.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_20220730.EditMyPlaceActivity
import com.neppplus.keepthetime_20220730.MyPlaceActivity
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.api.APIList
import com.neppplus.keepthetime_20220730.api.ServerApi
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.PlaceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPlaceRecyclerViewAdapter(
    val mContext: Context,
    val mList: ArrayList<PlaceData>
) : RecyclerView.Adapter<MyPlaceRecyclerViewAdapter.MyPlaceViewHolder>() {

    inner class MyPlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: PlaceData) {

            val apiList = ServerApi.getRetrofit(mContext).create(APIList::class.java)

            val nameTxt = itemView.findViewById<TextView>(R.id.placeNameTxt)
            val primeTxt = itemView.findViewById<TextView>(R.id.primeTxt)

            nameTxt.text = item.name
            if (item.is_primary) {
                primeTxt.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
//                val myIntent = Intent(mContext, EditMyPlaceActivity::class.java)
//                myIntent.putExtra("placeData", item)
//                mContext.startActivity(myIntent)

//                출발 장소 삭제 처리 Event
                AlertDialog.Builder(mContext)
                    .setTitle("장소 삭제")
                    .setMessage("${item.name}을/를 삭제처리하겠습니까?")
                    .setPositiveButton("삭제", DialogInterface.OnClickListener { dialogInterface, i ->

//                        만약 삭제하려는 장소가 기본 출발장소라면 삭제 처리 진행 X
                        if (item.is_primary) {
                            Toast.makeText(mContext, "기본 장소는 삭제할 수 없습니다.", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else {

//                        실제 장소를 삭제하고 / 액티비티 recyclerView도 refresh
                            apiList.getRequestDeleteMyPlace(
                                item.id
                            ).enqueue(object : Callback<BasicResponse> {
                                override fun onResponse(
                                    call: Call<BasicResponse>,
                                    response: Response<BasicResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(mContext, "장소가 삭제되었습니다.", Toast.LENGTH_SHORT)
                                            .show()

                                        (mContext as MyPlaceActivity).getMyPlaceFromServer()
                                    }
                                }

                                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                                }
                            })
                        }
                    })
                    .setNegativeButton("취소", null)
                    .show()
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