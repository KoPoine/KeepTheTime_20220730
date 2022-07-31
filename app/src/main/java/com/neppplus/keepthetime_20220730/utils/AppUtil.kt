package com.neppplus.keepthetime_20220730.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Response

class AppUtil {

    companion object {

        fun getMessageFromErrorBody(
            response : Response<BasicResponse>,
            context: Context
        ) {
            val errorBodyStr = response.errorBody()!!.string()
            val jsonObj = JSONObject(errorBodyStr)
            val code = jsonObj.getInt("code")
            val message = jsonObj.getString("message")

            if (code == 400) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            else {
                Log.e("code : $code", "message : $message")
            }
        }

    }

}