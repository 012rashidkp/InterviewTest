package com.example.interviewtest.Models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("token")
    val token: String,
    @SerializedName("message")
    val message:String,
)