package com.neppplus.keepthetime_20220730.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_20220730.*
import com.neppplus.keepthetime_20220730.databinding.FragmentSettingBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.utils.ContextUtil
import com.neppplus.keepthetime_20220730.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//        준비 시간 설정
        mBinding.readyTimeLayout.setOnClickListener {

            val customView = LayoutInflater.from(mContext).inflate(R.layout.custom_alert_dialog, null)

            val alert = AlertDialog.Builder(mContext)
                .setView(customView)
                .create()

//            Dialog 내부 코드 작성
            val titleTxt = customView.findViewById<TextView>(R.id.titleTxt)
            val bodyTxt = customView.findViewById<TextView>(R.id.bodyTxt)
            val inputEdt = customView.findViewById<EditText>(R.id.inputEdt)
            val positiveBtn = customView.findViewById<Button>(R.id.positiveBtn)
            val negativeBtn = customView.findViewById<Button>(R.id.negativeBtn)

            titleTxt.text = "준비 시간 설정"
            bodyTxt.text = "준비하는데 걸리는 시간을\n'분' 단위로 입력해주세요."
            inputEdt.hint = "'분' 단위로 입력"

            positiveBtn.setOnClickListener {
//            positiveBtn ClickEvent > 준비 시간 조절 API
                apiList.getRequestEditUser(
                    "ready_minute", inputEdt.text.toString()
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            GlobalData.loginUser = br.data.user

                            Toast.makeText(mContext, "준비 시작이 변경되었습니다.", Toast.LENGTH_SHORT).show()

                            mBinding.myReadyTimeTxt.text = "${br.data.user.ready_minute}분"

                            alert.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }

            negativeBtn.setOnClickListener {
                alert.dismiss()
            }



//            실제 만들어놓은 다이얼로그 열기
            alert.show()


        }

//        비밀번호 변경 Event
        mBinding.editPwLayout.setOnClickListener {
            val customView = LayoutInflater.from(mContext).inflate(R.layout.custom_alert_dialog, null)

            val alert = AlertDialog.Builder(mContext)
                .setView(customView)
                .create()

            val titleTxt = customView.findViewById<TextView>(R.id.titleTxt)
            val bodyTxt = customView.findViewById<TextView>(R.id.bodyTxt)
            val inputEdt = customView.findViewById<EditText>(R.id.inputEdt)
            val inputEdt2 = customView.findViewById<EditText>(R.id.inputEdt2)
            val positiveBtn = customView.findViewById<Button>(R.id.positiveBtn)
            val negativeBtn = customView.findViewById<Button>(R.id.negativeBtn)

            bodyTxt.visibility = View.GONE
            inputEdt2.visibility = View.VISIBLE

            titleTxt.text = "비밀 번호 변경"
            inputEdt.hint = "이전 비밀번호 입력"
            inputEdt2.hint = "새 비밀번호 입력"

//            도전과제 : EditText의 InputType 변경, imeOption 변경
//            hint : Kotlin EditText change InputType programmatically > 동적으로 ~~을 변경하는 방법

            positiveBtn.setOnClickListener {
                apiList.getRequestChangePw(
                    inputEdt.text.toString(), inputEdt2.text.toString()
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            Toast.makeText(mContext, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show()

                            ContextUtil.setLoginToken(mContext, br.data.token)

                            alert.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }

            negativeBtn.setOnClickListener {
                alert.dismiss()
            }

            alert.show()
        }

//        내 출발 장소 관리
        mBinding.myPlaceLayout.setOnClickListener {
            val myIntent = Intent(mContext, MyPlaceActivity::class.java)
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