package com.example.interviewtest.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.interviewtest.Fragments.DefaultListFragment
import com.example.interviewtest.R
import com.example.interviewtest.databinding.ActivityLoginBinding
import com.example.interviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DefaultListFragment())
            .commit()
    }
}