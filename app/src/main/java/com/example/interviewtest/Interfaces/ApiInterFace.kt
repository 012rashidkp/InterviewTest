package com.example.interviewtest.Interfaces

import com.example.interviewtest.Models.CreateUserResponse
import com.example.interviewtest.Models.DetailResponse
import com.example.interviewtest.Models.ListUserResponse
import com.example.interviewtest.Models.LoginResponse
import com.example.interviewtest.Network.Apis.Companion.AddNewUser
import com.example.interviewtest.Network.Apis.Companion.DetailUser
import com.example.interviewtest.Network.Apis.Companion.Login
import com.example.interviewtest.Network.Apis.Companion.UsersList
import retrofit2.http.*

interface ApiInterFace {
    @FormUrlEncoded
    @POST(Login)
    suspend fun LoginUser(@Field("username") username:String,
                          @Field("password") password:String): LoginResponse

    @FormUrlEncoded
    @POST(AddNewUser)
    suspend fun Createuser(@Header("Authorization") token: String, @Field("fname") fname:String,
                           @Field("lname") lname:String,
                           @Field("email")email:String,
                           @Field("phone")phone:String,
                           @Field("experience")experience:String): CreateUserResponse

  @GET(UsersList)
  suspend fun ListUsers(@Header("Authorization") token: String):ListUserResponse

    @GET(DetailUser)
    suspend fun DetailsUser(@Header("Authorization") token: String,@Path("id") id:String):DetailResponse
}