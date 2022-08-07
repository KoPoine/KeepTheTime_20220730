package com.neppplus.keepthetime_20220730

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.adapters.FriendSpinnerAdapter
import com.neppplus.keepthetime_20220730.adapters.StartPlaceSpinnerAdapter
import com.neppplus.keepthetime_20220730.databinding.ActivityEditAppointmentAcitivtyBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.FriendData
import com.neppplus.keepthetime_20220730.datas.PlaceData
import com.neppplus.keepthetime_20220730.datas.UserData
import com.neppplus.keepthetime_20220730.utils.SizeUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditAppointmentActivity : BaseActivity() {

    lateinit var mBinding : ActivityEditAppointmentAcitivtyBinding

//    선택한 약속 일시를 저장할 멤버변수
    var mSelectedDateTime = Calendar.getInstance()  // 기본값 : 현재시간

//    스피너에 활용될 멤버변수
    val mPlaceList = ArrayList<PlaceData>()
    val mFriendList = ArrayList<FriendData>()

    lateinit var mPlaceAdapter : StartPlaceSpinnerAdapter
    lateinit var mFriendAdapter : FriendSpinnerAdapter

//    출발지 장소 저장
    lateinit var mSelectedStartPlace : PlaceData

//    초대된 친구들 목록
    val mSelectedFriendList = ArrayList<FriendData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment_acitivty)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        날짜 선택 버튼 클릭 이벤트
        mBinding.dateTxt.setOnClickListener {
//            날짜를 선택하고 할 일 (인터페이스)을 작성
            val dl = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {

                    mSelectedDateTime.set(year, month, day)  // 사용자가 선택한 날짜를 멤버변수에 기록

                    val sdf = SimpleDateFormat("yy. M. d (E)")

                    Log.d("선택된 시간", sdf.format(mSelectedDateTime.time))

                    mBinding.dateTxt.text = sdf.format(mSelectedDateTime.time)
                }
            }

//            DatePickerDialog 팝업
            val dpd = DatePickerDialog(
                mContext,
                dl,
                mSelectedDateTime.get(Calendar.YEAR),
                mSelectedDateTime.get(Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH),
            ).show()
        }

//        시간 선택 버튼 클릭 이벤트
        mBinding.timeTxt.setOnClickListener {
            val tsl = object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
                    mSelectedDateTime.set(Calendar.HOUR_OF_DAY, hour)
                    mSelectedDateTime.set(Calendar.MINUTE, minute)

                    val sdf = SimpleDateFormat("a h시 m분")
                    mBinding.timeTxt.text = sdf.format(mSelectedDateTime.time)
                }
            }

            TimePickerDialog(
                mContext,
                tsl,
                mSelectedDateTime.get(Calendar.HOUR_OF_DAY),
                mSelectedDateTime.get(Calendar.MINUTE),
                false
            ).show()
        }

//        출발장소 스피너 선택 이벤트
        mBinding.startPlaceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                mSelectedStartPlace = mPlaceList[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

//        친구 초대 버튼 클릭 이벤트 처리
        mBinding.inviteBtn.setOnClickListener {
//            지금 선택된 친구가 누구인지 확인 => 스피너에 선택되어있는 아이템의 포지션 확인
            val selectedFriend = mFriendList[mBinding.friendSpinner.selectedItemPosition]

//            Toast.makeText(mContext, selectedFriend.nick_name, Toast.LENGTH_SHORT).show()

//            친구추가 버튼을 눌렀는데 > 이미 선택이 되어있을 경우 > 분기 처리를 통해서 작업 진행(반복문)
//            for (friend in mSelectedFriendList) {
//                if (friend == selectedFriend) {
//                    Toast.makeText(mContext, "이미 추가한 친구입니다.", Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
//            }
            if (mSelectedFriendList.contains(selectedFriend)) {
                Toast.makeText(mContext, "이미 추가한 친구입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


//            텍트스뷰 하나를 동적으로 생성(코틀린에서 생성)
            val textView = TextView(mContext)
            textView.text = selectedFriend.nick_name

//            우리가 만든 drawable을 TextView 적용 (동적으로 background setting)
            textView.setBackgroundResource(R.drawable.cumtom_textview_rectangle_bg)

//            텍스트뷰에 패딩을 동적으로 설정 ( kt파일에서 size를 조정 > px단위로 조정 )
            textView.setPadding(SizeUtil.dpToPx(mContext, 10f))

//            Float(실수), Long(정수) : 작성시 값 뒤에 F / L을 붙여줘야함.
//            val float : Float = 5.0f

//            텍스트뷰에 동적으로 마진 설정 [도전과제]

//            텍스트뷰 클릭시 추가된 친구목록 삭제 => 텍스트뷰 클릭 이벤트처리
            textView.setOnClickListener {
//                초대된 친구 목록(mSelectedFriendList)에서 선택된 친구(selectedFriend)를 삭제
                mSelectedFriendList.remove(selectedFriend)  // ArrayList의 기능(remove) 활용

//                동적으로 추가된 textView 역시 삭제 (View를 삭제)
                mBinding.friendListLayout.removeView(textView)
            }

//            flowLayout에 만들어놓은 텍스트뷰를 추가 > flowLayout가 addView 함수 사용 TextView를 추가
            mBinding.friendListLayout.addView(textView)
            mSelectedFriendList.add(selectedFriend)
        }

//        약속 저장하기
        mBinding.saveBtn.setOnClickListener {

        }
    }

    override fun setValues() {
        mPlaceAdapter = StartPlaceSpinnerAdapter(mContext, R.layout.place_list_item, mPlaceList)
        mBinding.startPlaceSpinner.adapter = mPlaceAdapter

        mFriendAdapter = FriendSpinnerAdapter(mContext, R.layout.friend_list_item, mFriendList)
        mBinding.friendSpinner.adapter = mFriendAdapter

        getMyPlaceListFromServer()
        getMyFriendListFromServer()
    }

    fun getMyPlaceListFromServer() {
        apiList.getRequestMyPlace().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {

                    mPlaceList.clear()
                    mPlaceList.addAll(response.body()!!.data.places)
                    mPlaceAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    fun getMyFriendListFromServer() {
        apiList.getRequestFriendsList("my").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    mFriendList.clear()
                    mFriendList.addAll(response.body()!!.data.friends)
                    mFriendAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}