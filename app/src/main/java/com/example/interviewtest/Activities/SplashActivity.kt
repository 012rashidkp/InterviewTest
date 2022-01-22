package com.example.interviewtest.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.example.interviewtest.R
import com.example.interviewtest.databinding.ActivityLoginBinding
import com.example.interviewtest.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private var binding:ActivitySplashBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        LaunchApp()
    }
    fun LaunchApp(){
        Handler().postDelayed({
            val intent=Intent(this,LoginActivity::class.java)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            startActivity(intent)
            finish()
        },3000)
    }
}