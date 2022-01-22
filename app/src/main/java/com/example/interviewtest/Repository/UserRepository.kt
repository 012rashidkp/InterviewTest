package com.example.interviewtest.Repository

import com.example.interviewtest.Network.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {
    suspend fun getLogin(username:String,password:String)=apiHelper.getLogin(username,password)
    suspend fun SaveData(token: String, firstname: String, lastname: String, email: String, phone: String, exp: String)=apiHelper.SaveData(token,firstname,lastname,email,phone,exp)
    suspend fun Allusers(token: String)=apiHelper.AllUsers(token)
    suspend fun Detailuser(token: String,id:String)=apiHelper.DetailUser(token,id)
}