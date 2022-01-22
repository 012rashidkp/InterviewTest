package com.example.interviewtest.Network

import com.example.interviewtest.Interfaces.ApiInterFace

class ApiHelper(private val apiInterFace: ApiInterFace) {
    suspend fun getLogin(username:String,password:String)=apiInterFace.LoginUser(username,password)
    suspend fun SaveData(token: String, firstname: String, lastname: String, email: String, phone: String, exp: String)=apiInterFace.Createuser(token,firstname,lastname,email,phone,exp)
    suspend fun AllUsers(token:String)=apiInterFace.ListUsers(token)

    suspend fun DetailUser(token:String,id:String)=apiInterFace.DetailsUser(token,id)
}