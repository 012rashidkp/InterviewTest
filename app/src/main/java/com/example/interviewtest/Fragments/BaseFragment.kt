package com.example.interviewtest.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtest.Network.ApiClient
import com.example.interviewtest.Network.ApiHelper
import com.example.interviewtest.R
import com.example.interviewtest.Utils.TinyDb
import com.example.interviewtest.ViewModel.UserViewModel
import com.example.interviewtest.ViewModelFactory.UserViewModelFactory


open class BaseFragment : Fragment() {
    lateinit var viewmodel: UserViewModel
    lateinit var tinyDb: TinyDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return view
    }
    fun InitTinyDb(){
        tinyDb= TinyDb(requireContext())
    }
fun InitViewModel(){
    viewmodel= ViewModelProvider(
        this,
        UserViewModelFactory(ApiHelper(ApiClient.invoke()))
    )[UserViewModel::class.java]
}
}