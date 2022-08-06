package com.neppplus.keepthetime_20220730

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KeepTheTime : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "ecb0cc6522d9bee1469315693d6e74c3")
    }
}