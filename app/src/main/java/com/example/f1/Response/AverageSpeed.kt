package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class AverageSpeed(
    @SerializedName("speed")
    val speed: String,
    @SerializedName("units")
    val units: String
)