package com.example.interviewtest.Fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewtest.Adapter.UserListAdapter
import com.example.interviewtest.Interfaces.ItemClickListener
import com.example.interviewtest.Network.Status
import com.example.interviewtest.R
import com.example.interviewtest.Utils.AppUtils
import com.example.interviewtest.Utils.AppUtils.Companion.DisplayMessage
import com.example.interviewtest.Utils.NavigationUtils
import com.example.interviewtest.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment() {
private var binding:FragmentDetailBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(inflater, container, false)
InitTinyDb()
InitViewModel()
val token=tinyDb.getString("token")
val id=tinyDb.getString("itemid")
MakeApiCall(token = "Bearer ${token}",id!!)
        return binding!!.root


    }

    private fun MakeApiCall(token: String, id: String) {
        viewmodel.Detailuser(token,id).observe(requireActivity(),{
            it.let { resources ->
                when(resources.status){
                    Status.SUCCESS->{
                        resources.data.let { result->
                            if (result!!.status){


                                Handler().postDelayed({
                                    binding!!.detailprogressbar.visibility=View.GONE
                                    binding!!.viewlayout.visibility=View.VISIBLE
                                    binding!!.detailemail.text=result.data.email
                                    binding!!.detailcreatedat.text=result.data.createdAt
                                    binding!!.detailfname.text=result.data.fname
                                    binding!!.detaillname.text=result.data.lname
                                    binding!!.detailphone.text= result.data.phone
                                    binding!!.detailexp.text=result.data.experience

                                },2500)
                            }
                            if(!result.status){
                                binding!!.detailprogressbar.visibility=View.GONE

                                DisplayMessage(binding!!.detailscreen, "something went wrong", R.drawable.error)

                            }
                        }
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR->{
                        binding!!.detailprogressbar.visibility=View.GONE
                        DisplayMessage(binding!!.detailscreen, "something went wrong", R.drawable.error)

                    }
                }
            }
        })

    }


}