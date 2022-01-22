package com.example.interviewtest.Models

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val `data`: Data
)