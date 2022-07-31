package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
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

        backBtn.visibility = View.GONE

        mPagerAdapter = MainViewPagerAdapter(this)
        mBinding.mainViewPager.adapter = mPagerAdapter

//        뷰페이저 연동 event
        mBinding.mainViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mBinding.bottomNav.menu.getItem(position).isChecked = true
                }
            }
        )

//        bottomNav 연동 event
        mBinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId)  {
                R.id.calendar -> mBinding.mainViewPager.currentItem = 0
                R.id.invite -> mBinding.mainViewPager.currentItem = 1
                R.id.setting -> mBinding.mainViewPager.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }
    }
}