package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("time")
    val time: String
)