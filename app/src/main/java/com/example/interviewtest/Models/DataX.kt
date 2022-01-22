package com.example.interviewtest.Models

import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("__v")
    val __v: Int,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("experience")
    val experience: String,
    @SerializedName("fname")
    val fname: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lname")
    val lname: String,
    @SerializedName("phone")
    val phone: String
)