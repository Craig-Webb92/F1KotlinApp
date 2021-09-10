package com.example.f1.Response


import com.google.gson.annotations.SerializedName

data class JSONResponse(
    @SerializedName("MRData")
    val mRData: MRData
)