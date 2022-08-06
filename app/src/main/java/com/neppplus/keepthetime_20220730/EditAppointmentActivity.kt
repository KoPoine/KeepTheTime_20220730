package com.neppplus.keepthetime_20220730

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.databinding.ActivityEditAppointmentAcitivtyBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var mBinding : ActivityEditAppointmentAcitivtyBinding

//    선택한 약속 일시를 저장할 멤버변수
    var mSelectedDateTime = Calendar.getInstance()  // 기본값 : 현재시간

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
    }

    override fun setValues() {

    }
}