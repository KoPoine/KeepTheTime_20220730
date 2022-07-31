package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.neppplus.keepthetime_20220730.databinding.ActivityEditMyPlaceBinding

class EditMyPlaceActivity : BaseActivity() {

    lateinit var mBinding : ActivityEditMyPlaceBinding

//    국산 지도맵은 널아일랜드(0.0 / 0.0)로 갈 수 없기에 초기값있을시 멤버변수에 바로 대입해주자.
    var mSelectedLatitude = 37.5779235853308
    var mSelectedLongitude = 127.033553463647

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit_my_place)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        mBinding.saveBtn.setOnClickListener {
//            API 통신 > 저장 (latitude / longitude)
//            1. 장소이름 넣었나요?

//            2. 기본장소인가요?

        }
    }

    override fun setValues() {
        titleTxt.text = "새 출발 장소"
        backBtn.visibility = View.VISIBLE

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync {

            val naverMap = it

            val coord = LatLng(37.5779235853308, 127.033553463647)

            val cameraUpdate = CameraUpdate.scrollTo(coord)
            naverMap.moveCamera(cameraUpdate)

            val marker = Marker()
            marker.position = coord
            marker.map = naverMap

            naverMap.setOnMapClickListener { pointF, latLng ->
                marker.position = latLng
                marker.map = naverMap

                mSelectedLatitude = latLng.latitude
                mSelectedLongitude = latLng.longitude
            }
        }
    }
}