package com.neppplus.keepthetime_20220730.utils

import com.neppplus.keepthetime_20220730.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Response

class AppUtil {

    companion object {

        fun getMessageFromErrorBody(
            response : Response<BasicResponse>
        ) :String {
            val errorBodyStr = response.errorBody()!!.string()
            val jsonObj = JSONObject(errorBodyStr)
            val message = jsonObj.getString("message")

            return message
        }

    }

}