package com.neppplus.keepthetime_20220730.datas

import java.io.Serializable

data class AppointmentData(
    val id : Int,
    val title : String,
    val place : String,
    val latitude : Double,
    val longitude : Double,
    val invited_friends : ArrayList<UserData>,
    val dateTime : String
) : Serializable
