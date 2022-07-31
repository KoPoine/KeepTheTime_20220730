package com.neppplus.keepthetime_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.neppplus.keepthetime_20220730.databinding.ActivityEditMyPlaceBinding
import com.neppplus.keepthetime_20220730.datas.BasicResponse
import com.neppplus.keepthetime_20220730.utils.AppUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val inputName = mBinding.placeNameEdt.text.toString()
            if (inputName.isBlank()) {
                Toast.makeText(mContext, "장소명은 공백 / 빈칸 일 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            2. 기본장소인가요?
            val isPrimary = mBinding.primaryPlaceCb.isChecked

//            3. 실제 API 연결
            apiList.getRequestAddMyPlace(
                inputName, mSelectedLatitude, mSelectedLongitude, isPrimary
            ).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(mContext, "장소가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        AppUtil.getMessageFromErrorBody(response, mContext)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
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