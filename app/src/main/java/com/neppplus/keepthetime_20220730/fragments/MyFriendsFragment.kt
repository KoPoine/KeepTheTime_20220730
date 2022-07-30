package com.neppplus.keepthetime_20220730.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.databinding.FragmentMyFriendsBinding

class MyFriendsFragment : BaseFragment() {

    lateinit var mBinding : FragmentMyFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_friends, container, false)
        return mBinding.root
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}