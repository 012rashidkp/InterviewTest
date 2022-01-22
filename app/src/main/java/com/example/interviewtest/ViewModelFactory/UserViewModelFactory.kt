package com.example.interviewtest.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtest.Network.ApiHelper
import com.example.interviewtest.Repository.UserRepository
import com.example.interviewtest.ViewModel.UserViewModel

class UserViewModelFactory(private val apihelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UserRepository(apihelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }





}