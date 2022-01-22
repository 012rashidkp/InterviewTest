package com.example.interviewtest.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.interviewtest.Fragments.CreateFragment
import com.example.interviewtest.Fragments.DefaultListFragment
import com.example.interviewtest.Fragments.DetailFragment
import com.example.interviewtest.R

class NavigationUtils : Fragment() {
companion object{
    fun DefaultFragment(activity: FragmentActivity){
        activity.supportFragmentManager.
        beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
            .addToBackStack(null).replace(R.id.fragment_container, DefaultListFragment()).commit()
    }
    fun AddingFragment(activity: FragmentActivity){
        activity.supportFragmentManager.
        beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
            .addToBackStack(null).replace(R.id.fragment_container,CreateFragment()).commit()
    }
    fun Detailviewfragment(activity: FragmentActivity){
        activity.supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
            .addToBackStack(null).replace(R.id.fragment_container,DetailFragment()).commit()
    }
}

}