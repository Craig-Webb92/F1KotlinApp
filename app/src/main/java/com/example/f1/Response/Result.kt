package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("Constructor")
    val `constructor`: Constructor,
    @SerializedName("Driver")
    val driver: Driver,
    @SerializedName("FastestLap")
    val fastestLap: FastestLap,
    @SerializedName("grid")
    val grid: String,
    @SerializedName("laps")
    val laps: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("points")
    val points: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("positionText")
    val positionText: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("Time")
    val time: Time
)