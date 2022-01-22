package com.example.interviewtest.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtest.Network.ApiClient
import com.example.interviewtest.Network.ApiHelper
import com.example.interviewtest.Network.Status
import com.example.interviewtest.R
import com.example.interviewtest.Utils.AppUtils.Companion.DisplayMessage
import com.example.interviewtest.Utils.AppUtils.Companion.hideSoftKeyBoard
import com.example.interviewtest.Utils.TinyDb
import com.example.interviewtest.ViewModel.UserViewModel
import com.example.interviewtest.ViewModelFactory.UserViewModelFactory
import com.example.interviewtest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
private var binding:ActivityLoginBinding?=null
    lateinit var viewmodel:UserViewModel
    lateinit var tinyDb: TinyDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        InitViewModel()
        tinyDb= TinyDb(this)
        binding!!.loginbtn.setOnClickListener{
          val username=binding!!.loginusernameedit.text.toString()
          val password=binding!!.loginpasswordedit.text.toString()
          if (validated()){
              hideSoftKeyBoard(this,binding!!.loginview)
              binding!!.loginprogress.visibility=View.VISIBLE
              binding!!.logintext.visibility=View.GONE
              MakeApiCall(username,password)
          }
            else{
                DisplayMessage(binding!!.loginview,"please complete login details",R.drawable.error)
          }
        }

    }

    private fun InitViewModel() {
        viewmodel= ViewModelProvider(
            this,
            UserViewModelFactory(ApiHelper(ApiClient.invoke()))
        )[UserViewModel::class.java]
    }

    private fun MakeApiCall(username: String, password: String) {
        System.out.println("username $username password $password")
        viewmodel.getLogin(username,password).observe(this, Observer {
            it?.let { response ->
                when(response.status){
                    Status.SUCCESS->{

                        response.data?.let { datas ->
                            if (datas.status){
                                System.out.println("true")
                                Loading()
                                tinyDb.putString("token",datas.token)

                            }
                          else if (!datas.status){
                              System.out.println("false")
                                hideSoftKeyBoard(this,binding!!.loginview)
                              binding!!.loginprogress.visibility=View.GONE
                              binding!!.logintext.visibility=View.VISIBLE
                              DisplayMessage(binding!!.loginview,datas.message,R.drawable.error)
                            }


                        }
                    }
                    Status.LOADING->{
                   System.out.println("loading")
                    }
                    Status.ERROR->{
                        System.out.println("error")
                        hideSoftKeyBoard(this,binding!!.loginview)
                        binding!!.loginprogress.visibility=View.GONE
                        binding!!.logintext.visibility=View.VISIBLE
                        DisplayMessage(binding!!.loginview,"something went wrong",R.drawable.error)
                    }
                }
            }
        })
        


    }

    fun validated():Boolean{
        if (binding!!.loginusernameedit.text.toString().equals("")){
            binding!!.loginusernameedit.setError("please enter your username")
            return false
        }
        else if (binding!!.loginpasswordedit.text.toString().equals("")){
            binding!!.loginpasswordedit.setError("please enter your password")
            return false
        }
        return true
    }
    fun Loading(){
        Handler().postDelayed({
            hideSoftKeyBoard(this,binding!!.loginview)
        binding!!.loginprogress.visibility=View.GONE
        binding!!.logintext.visibility=View.VISIBLE
        DisplayMessage(binding!!.loginview,"login Success",R.drawable.success)
            val intent=Intent(this,MainActivity::class.java)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            startActivity(intent)
            finish()


        },3500)
    }
}