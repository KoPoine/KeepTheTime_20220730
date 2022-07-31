package com.neppplus.keepthetime_20220730.datas

data class DataResponse(
    val code : Int,
    val user : UserData,
    val token : String,
    val friends : ArrayList<FriendData>,
    val users : ArrayList<UserData>,
    val places : ArrayList<PlaceData>
)
