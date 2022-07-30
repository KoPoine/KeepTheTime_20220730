package com.neppplus.keepthetime_20220730.api

import android.content.Context
import com.neppplus.keepthetime_20220730.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerApi {

    companion object {

//        서버주소 멤버변수화
        private val baseUrl = "https://keepthetime.xyz"

//        retrofit 초기화 진행
        private var retrofit : Retrofit? = null

        fun getRetrofit(context : Context) : Retrofit {

            if (retrofit == null) {

//                Api 요청이 발생하면 => 요청을 가로채서(intercept) => 우리의 헤더를 추가해주자
//                자동으로 헤더를 달아주는 효과 발생
                val interceptor = Interceptor {
                    with(it) {
                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getLoginToken(context))
                            .build()
                        proceed(newRequest)
                    }
                }

//                retrofit : OKHttp의 확장판 => retrofit OkHttpClient 형태의 클라이언트를 활용 (커스터마이징 가능)

                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit!!
        }

    }

}