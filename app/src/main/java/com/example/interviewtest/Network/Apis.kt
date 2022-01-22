package com.example.interviewtest.Network

class Apis {
    companion object{
        val BaseUrl="http://3.7.41.23/api/v1/"
        const val Login="login"
        const val UsersList="users"
        const val AddNewUser= UsersList
        const val DetailUser= AddNewUser+"/{id}"


    }
}