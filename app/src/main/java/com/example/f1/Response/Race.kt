package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class Race(
    @SerializedName("Circuit")
    val circuit: Circuit,
    @SerializedName("date")
    val date: String,
    @SerializedName("raceName")
    val raceName: String,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("url")
    val url: String
)