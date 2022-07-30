package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.databinding.ActivityFriendListBinding

class FriendListActivity : BaseActivity() {

    lateinit var mBinding : ActivityFriendListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_friend_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}