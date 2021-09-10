package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class StandingsTable(
    @SerializedName("season")
    val season: String,
    @SerializedName("StandingsLists")
    val standingsLists: List<StandingsLists>
)