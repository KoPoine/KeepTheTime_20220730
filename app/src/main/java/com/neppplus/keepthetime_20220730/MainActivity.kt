package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.adapters.MainViewPagerAdapter
import com.neppplus.keepthetime_20220730.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var mBinding : ActivityMainBinding

    lateinit var mPagerAdapter : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mPagerAdapter = MainViewPagerAdapter(this)
        mBinding.mainViewPager.adapter = mPagerAdapter
    }
}