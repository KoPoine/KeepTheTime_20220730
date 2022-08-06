package com.neppplus.keepthetime_20220730

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.adapters.StartPlaceSpinnerAdapter
import com.neppplus.keepthetime_20220730.databinding.ActivityEditAppointmentAcitivtyBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.datas.PlaceData
import com.neppplus.keepthetime_20220730.datas.UserData
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
    val mFriendList = ArrayList<UserData>()

//    출발지 장소 저장
    lateinit var mSelectedStartPlace : PlaceData

    lateinit var mPlaceAdapter : StartPlaceSpinnerAdapter

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
    }

    override fun setValues() {
        mPlaceAdapter = StartPlaceSpinnerAdapter(mContext, R.layout.place_list_item, mPlaceList)
        mBinding.startPlaceSpinner.adapter = mPlaceAdapter

        getMyPlaceListFromServer()
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
}