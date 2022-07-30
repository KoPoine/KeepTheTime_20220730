package com.neppplus.keepthetime_20220730.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.databinding.FragmentInvitedBinding

class InvitedFragment : Fragment() {

    lateinit var mBinding : FragmentInvitedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_invited, container, false)
        return mBinding.root
    }

}