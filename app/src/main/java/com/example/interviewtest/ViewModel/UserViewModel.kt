package com.example.interviewtest.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.interviewtest.Network.Resources
import com.example.interviewtest.Repository.UserRepository
import kotlinx.coroutines.Dispatchers

class UserViewModel(private val userRepository: UserRepository):ViewModel() {

    fun getLogin(username:String,password:String) = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = userRepository.getLogin(username,password)))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun SaveData(token: String, firstname: String, lastname: String, email: String, phone: String, exp: String) = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = userRepository.SaveData(token,firstname,lastname,email,phone,exp)))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun Allusers(token: String) = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = userRepository.Allusers(token)))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun Detailuser(token: String,id:String) = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = userRepository.Detailuser(token,id)))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}