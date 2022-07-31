package com.neppplus.keepthetime_20220730

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.neppplus.keepthetime_20220730.api.APIList
import com.neppplus.keepthetime_20220730.api.ServerApi
import retrofit2.Retrofit
import retrofit2.create

abstract class BaseActivity : AppCompatActivity(){

    lateinit var mContext : Context

    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    lateinit var titleTxt : TextView
    lateinit var backBtn : ImageView
    lateinit var addBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        retrofit = ServerApi.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)

        supportActionBar?.let {
            setCustomActionBar()

            backBtn.setOnClickListener { finish() }
        }
    }

    abstract fun setupEvents()

    abstract fun setValues()

    fun setCustomActionBar () {
        val defActionBar = supportActionBar!!

        defActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defActionBar.setCustomView(R.layout.custom_action_bar)

        val toolbar = defActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

        titleTxt = defActionBar.customView.findViewById(R.id.titleTxt)
        backBtn = defActionBar.customView.findViewById(R.id.backBtn)
        addBtn = defActionBar.customView.findViewById(R.id.addBtn)
    }

}