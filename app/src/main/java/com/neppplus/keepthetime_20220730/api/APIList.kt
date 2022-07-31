package com.neppplus.keepthetime_20220730.api

import com.neppplus.keepthetime_20220730.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

//    Search
//    친구 닉네임으로 검색하기
    @GET("/search/user")
    fun getRequestSearchUser(
        @Query("nickname") nick : String
    ) : Call<BasicResponse>

    //user
    //이메일 / 닉네임 중복 체크
    @GET("/user/check")
    fun getRequestUserCheck(
        @Query("type") type : String,
        @Query("value") value : String
    ) : Call<BasicResponse>

//    회원가입
    @FormUrlEncoded
    @PUT ("/user")
    fun getRequestSignUp (
        @Field ("email") email : String,
        @Field ("password") password : String,
        @Field ("nick_name") nick : String
    ) : Call<BasicResponse>

//    로그인
    @FormUrlEncoded
    @POST ("/user")
    fun getRequestLogin (
        @Field ("email") email: String,
        @Field ("password") password: String
    ) : Call<BasicResponse>

//    내정보 확인

    @GET("/user")
    fun getRequestMyInfo(
        @Header ("X-Http-Token") token : String
    ) : Call<BasicResponse>

//    회원 정보 수정
    @FormUrlEncoded
    @PATCH("/user")
    fun getRequestEditUser(
        @Field ("field") type: String,
        @Field ("value") value : String
    ) : Call<BasicResponse>

//    비밀 번호 변경
    @FormUrlEncoded
    @PATCH("/user/password")
    fun getRequestChangePw (
        @Field ("current_password") currentPw : String,
        @Field ("new_password") newPw : String
    ) : Call<BasicResponse>

//    user/friend
//    내친구 목록 불러오기

    @GET("/user/friend")
    fun getRequestFriendsList(
        @Header ("X-Http-Token") token : String,
        @Query ("type") type : String
    ) : Call<BasicResponse>
}