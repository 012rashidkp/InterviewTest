package com.example.interviewtest.Network

import com.example.interviewtest.Interfaces.ApiInterFace
import com.example.interviewtest.Network.Apis.Companion.BaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        operator fun invoke(): ApiInterFace = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterFace::class.java)
    }
}