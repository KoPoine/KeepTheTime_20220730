package com.neppplus.keepthetime_20220730.api

import com.neppplus.keepthetime_20220730.datas.BasicResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface APIList {
//    Appointment
    @GET("/appointment")
    fun getRequestMyAppointment () : Call<BasicResponse>

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

//    소셜로그인
    @FormUrlEncoded
    @POST("/user/social")
    fun getRequestLoginWithSocial(
        @Field ("provider") provider : String,
        @Field ("uid") uid : String,
        @Field ("nick_name") nick : String
    ) : Call<BasicResponse>

//    회원 프로필 이미지 수정
    @Multipart
    @PUT("/user/image")
    fun getRequestEditUserProfile(
        @Part profileImg : MultipartBody.Part
    ) : Call<BasicResponse>

//    user/friend
//    내친구 목록 불러오기
    @GET("/user/friend")
    fun getRequestFriendsList(
        @Query ("type") type : String
    ) : Call<BasicResponse>

//    친구 추가 요청하기
    @FormUrlEncoded
    @POST("/user/friend")
    fun getRequestAddFriend (
        @Field ("user_id") userId : Int
    ) : Call<BasicResponse>

//    친구 추가 수락 / 거절
    @FormUrlEncoded
    @PUT ("/user/friend")
    fun getRequestBooleanRequested (
        @Field ("user_id") userId : Int,
        @Field ("type") type : String
    ) : Call<BasicResponse>



//    user/place
//    내 장소 목록 불러오기
    @GET("/user/place")
    fun getRequestMyPlace() : Call<BasicResponse>

//    내 장소 추가하기
    @FormUrlEncoded
    @POST("/user/place")
    fun getRequestAddMyPlace(
        @Field ("name") name : String,
        @Field ("latitude") latitude : Double,
        @Field ("longitude") longitude : Double,
        @Field ("is_primary") isPrimary : Boolean
    ) : Call<BasicResponse>

//    장소 삭제하기
    @DELETE("/user/place")
    fun getRequestDeleteMyPlace (
        @Query ("place_id") placeId : Int
    ) : Call<BasicResponse>
}