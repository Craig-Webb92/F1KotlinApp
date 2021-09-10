package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class Constructor(
    @SerializedName("constructorId")
    val constructorId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("url")
    val url: String
)