package com.neppplus.keepthetime_20220730.datas

data class UserData(
    val id : Int,
    val email : String,
    val provider : String,
    val nick_name : String,
    val ready_minute : Int,
    val profile_img : String
)
