package com.neppplus.keepthetime_20220730.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.FriendListActivity
import com.neppplus.keepthetime_20220730.LoginActivity
import com.neppplus.keepthetime_20220730.MainActivity
import com.neppplus.keepthetime_20220730.R
import com.neppplus.keepthetime_20220730.databinding.FragmentSettingBinding
import com.neppplus.keepthetime_20220730.utils.ContextUtil
import com.neppplus.keepthetime_20220730.utils.GlobalData

class SettingFragment : BaseFragment() {

    lateinit var mBinding : FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        로그아웃
        mBinding.logoutLayout.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
//                    로그아웃 로직 진행

                    ContextUtil.setLoginToken(mContext, "")
                    GlobalData.loginUser = null

                    val myIntent = Intent(mContext, LoginActivity::class.java)
                    startActivity(myIntent)
                    requireActivity().finish()
                })
                .setNegativeButton("취소", null)
                .show()


        }

//        내 친구 목록 관리
        mBinding.friendListLayout.setOnClickListener {
            val myIntent = Intent(mContext, FriendListActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        val loginUser = GlobalData.loginUser!!

        Glide.with(mContext).load(loginUser.profile_img).into(mBinding.profileImg)
        mBinding.nickTxt.text = loginUser.nick_name
        mBinding.myReadyTimeTxt.text = "${loginUser.ready_minute}분"
    }

}