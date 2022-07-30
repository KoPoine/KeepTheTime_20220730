package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.databinding.ActivitySignUpBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.utils.AppUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var mBinding : ActivitySignUpBinding

    var emailOk = false
    var nickOk = false
    var pwOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        mBinding.signUpBtn.setOnClickListener {
//            1. 이메일 중복 체크
            if (!emailOk) {
//                이메일 중복 체크 X
                Toast.makeText(mContext, "이메일 중복 체크를 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            2. 비밀번호 중복 체크(EditText 2가지 비교)
            if (!pwOk) {
                Toast.makeText(mContext, "비밀번호 확인을 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            3. 닉네임 중복 체크
            if (!nickOk) {
//                닉네임 중복 체크 X
                Toast.makeText(mContext, "닉네임 중복 체크를 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            4. 실제 회원가입
            val inputEmail = mBinding.emailEdt.text.toString()
            val inputPw = mBinding.pwEdt.text.toString()
            val inputNick = mBinding.nickEdt.text.toString()

            apiList.getRequestSignUp(
                inputEmail,
                inputPw,
                inputNick
            ).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!
                        Toast.makeText(mContext, "${br.data.user.nick_name}님 가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        val message = AppUtil.getMessageFromErrorBody(response)

                        Log.d("SignUpActivity", message)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })

        }

//        이메일 중복 체크 API / 닉네임 중복 체크 API
        mBinding.emailCheckBtn.setOnClickListener {
            val inputEmail = mBinding.emailEdt.text.toString()
            dupValue("EMAIL", inputEmail)
        }

        mBinding.nickCheckBtn.setOnClickListener {
            val inputNick = mBinding.nickEdt.text.toString()
            dupValue("NICK_NAME", inputNick)
        }

//        비밀번호 확인 로직
        mBinding.pwCheckEdt.addTextChangedListener {
//            분기처리 => 지금 작성된 EditText의 값이 > pwEdt에 들어있는 값과 동일한가?
//            if (it.toString() == mBinding.pwEdt.text.toString()) {
//                pwOk = true
//            }

            pwOk = (it.toString() == mBinding.pwEdt.text.toString() &&  it.toString().isNotBlank())
        }


        mBinding.pwEdt.addTextChangedListener {
//            분기처리 => 지금 작성된 EditText의 값이 > pwCheckEdt에 들어있는 값과 동일한가?
            pwOk = (it.toString() == mBinding.pwCheckEdt.text.toString() &&  it.toString().isNotBlank())
        }

//        이메일 및 닉네임 중복 체크 후 EditText 수정하는 상황 처리
        mBinding.emailEdt.addTextChangedListener {
            emailOk = false
        }

        mBinding.nickEdt.addTextChangedListener {
            nickOk = false
        }
    }

    override fun setValues() {

    }

    fun dupValue(type : String, value : String) {
//        실제 값이 공백이 아닐경우에만, 작업 진행
        if (value.isNotBlank()) {
            //        들어오는 생성자에 따른 별도의 중복체크
            apiList.getRequestUserCheck(type, value).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    if (response.isSuccessful) {
                        val br = response.body()!!

                        Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

                        when (type) {
                            "EMAIL" -> emailOk = true
                            "NICK_NAME" -> nickOk = true
                        }
                    }

                    else {
                        val message = AppUtil.getMessageFromErrorBody(response)

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        when (type) {
                            "EMAIL" -> emailOk = false
                            "NICK_NAME" -> nickOk = false
                        }
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }
        else {
//            type에 따른 분기처리 해주는게 좋다.
            Toast.makeText(mContext, "이메일 / 닉네임 값은 공백일 수 없습니다.", Toast.LENGTH_SHORT).show()
        }



    }
}