package com.example.interviewtest.Models

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("result")
    val result: Int,
    @SerializedName("data")
    val `data`: List<DataX>
)