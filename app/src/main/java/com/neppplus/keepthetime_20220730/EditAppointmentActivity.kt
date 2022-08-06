package com.neppplus.keepthetime_20220730

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_20220730.databinding.ActivityEditAppointmentAcitivtyBinding

class EditAppointmentActivity : BaseActivity() {

    lateinit var mBinding : ActivityEditAppointmentAcitivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment_acitivty)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}