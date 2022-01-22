package com.example.interviewtest.Fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.interviewtest.Network.Status
import com.example.interviewtest.R
import com.example.interviewtest.Utils.AppUtils
import com.example.interviewtest.Utils.AppUtils.Companion.DisplayMessage
import com.example.interviewtest.Utils.AppUtils.Companion.hideSoftKeyBoard
import com.example.interviewtest.Utils.NavigationUtils.Companion.DefaultFragment
import com.example.interviewtest.databinding.FragmentCreateBinding


class CreateFragment : BaseFragment() {
private var binding:FragmentCreateBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateBinding.inflate(inflater, container, false)
         InitTinyDb()
        InitViewModel()

        binding!!.savebtn.setOnClickListener {
            val token=tinyDb.getString("token")
            val firstname=binding!!.firstnameedit.text.toString()
            val lastname=binding!!.lastnameedit.text.toString()
            val email=binding!!.emaildedit.text.toString()
            val phone=binding!!.phoneedit.text.toString()
            val exp=binding!!.expedit.text.toString()
            if (Validated()){
                binding!!.signupprogress.visibility=View.VISIBLE
                binding!!.signuptext.visibility=View.GONE
                hideSoftKeyBoard(requireContext(), binding!!.createview)
                MakeApiCall(token = "Bearer ${token}",firstname,lastname,email,phone,exp)
            }
            else{
                DisplayMessage(
                    binding!!.createview,
                    "please complete user details",
                    R.drawable.error
                )

            }
        }

        return binding!!.root
    }

    private fun MakeApiCall(token: String, firstname: String, lastname: String, email: String, phone: String, exp: String) {
        viewmodel.SaveData(token,firstname,lastname,email,phone,exp).observe(requireActivity(), Observer {
            it?.let { response ->
                when(response.status){
                    Status.SUCCESS->{

                        response.data?.let { datas ->
                            if (datas.status){
                                System.out.println("true")
                                Loading()


                            }
                            else if (!datas.status){
                                System.out.println("false")
                                hideSoftKeyBoard(requireContext(),binding!!.createview)


                                                            }


                        }
                    }
                    Status.LOADING->{
                        System.out.println("loading")
                    }
                    Status.ERROR->{
                        System.out.println("error")
                        hideSoftKeyBoard(requireContext(),binding!!.createview)
                        binding!!.signupprogress.visibility=View.GONE
                        binding!!.signuptext.visibility=View.VISIBLE
                        DisplayMessage(binding!!.createview,"something went wrong",R.drawable.error)
                    }
                }
            }
        })



    }

    fun Validated():Boolean{
    if (binding!!.firstnameedit.text.toString().equals("")){
        binding!!.firstnameedit.setError("please enter first name")
        return false
    }
    else if (binding!!.lastnameedit.text.toString().equals("")){
        binding!!.lastnameedit.setError("please enter last name")
        return  false
    }
    else if (binding!!.emaildedit.text.toString().equals("")){
        binding!!.emaildedit.setError("please enter email")
        return false
    }
    else if (binding!!.phoneedit.text.toString().equals("")){
        binding!!.phoneedit.setError("please enter phone number")
        return false
    }
    else if (binding!!.expedit.text.toString().equals("")){
        binding!!.expedit.setError("please enter experience")
        return false
    }
   return true
}
fun Loading(){
    Handler().postDelayed({
        binding!!.signupprogress.visibility=View.GONE
        binding!!.signuptext.visibility=View.VISIBLE
        DisplayMessage(binding!!.createview,"data saved success",R.drawable.success)
        DefaultFragment(requireActivity())
                          },3000)
}
}


