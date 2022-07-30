package com.neppplus.keepthetime_20220730

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neppplus.keepthetime_20220730.api.APIList
import com.neppplus.keepthetime_20220730.api.ServerApi
import retrofit2.Retrofit
import retrofit2.create

abstract class BaseActivity : AppCompatActivity(){

    lateinit var mContext : Context

    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        retrofit = ServerApi.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()

    abstract fun setValues()

}