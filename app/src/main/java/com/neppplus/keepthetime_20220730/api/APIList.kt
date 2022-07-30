package com.neppplus.keepthetime_20220730.api

import com.neppplus.keepthetime_20220730.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    //user
    //이메일 / 닉네임 중복 체크
    @GET("/user/check")
    fun getRequestUserCheck(
        @Query("type") type : String,
        @Query("value") value : String
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT ("/user")
    fun getRequestSignUp (
        @Field ("email") email : String,
        @Field ("password") password : String,
        @Field ("nick_name") nick : String
    ) : Call<BasicResponse>

}