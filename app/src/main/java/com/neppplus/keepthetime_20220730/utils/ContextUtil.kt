package com.neppplus.keepthetime_20220730.utils

import android.content.Context

class ContextUtil {

    companion object {
//        메모장 이름
        private val prefName = "KeepTheTime"
//        알아볼수 있는 메모 제목
        private val LOGIN_TOKEN = "LOGIN_TOKEN"

        fun setLoginToken (context: Context, token : String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_TOKEN, token).apply()
        }

        fun getLoginToken (context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_TOKEN, "")!!
        }

    }

}