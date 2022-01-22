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
import com.example.interviewtest.Models.DataX
import com.example.interviewtest.Network.Status
import com.example.interviewtest.R
import com.example.interviewtest.Utils.AppUtils
import com.example.interviewtest.Utils.NavigationUtils.Companion.AddingFragment
import com.example.interviewtest.Utils.NavigationUtils.Companion.Detailviewfragment
import com.example.interviewtest.databinding.FragmentDefaultListBinding

class DefaultListFragment : BaseFragment() {
private var binding:FragmentDefaultListBinding?=null
    lateinit var users: List<DataX>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding= FragmentDefaultListBinding.inflate(inflater, container, false)
        InitViewModel()
        InitTinyDb()
       binding!!.createbtn.setOnClickListener {
           AddingFragment(requireActivity())
       }
val token=tinyDb.getString("token")
MakeApiCall(token = "Bearer ${token}")

return binding!!.root

    }

    private fun MakeApiCall(token: String) {
viewmodel.Allusers(token).observe(requireActivity(),{
    it.let { resources ->
        when(resources.status){
            Status.SUCCESS->{
                resources.data.let { result->
                    if (result!!.status){
                        users=result.data
                       val madapter=UserListAdapter(requireContext(),users,object :ItemClickListener{
                           override fun ItemCLick(id: String) {
                               tinyDb.putString("itemid",id)
                               Detailviewfragment(requireActivity())
                           }

                       })
                        Handler().postDelayed({
                        binding!!.usersprogressbar.visibility=View.GONE
                        binding!!.rvlist.visibility=View.VISIBLE
                        binding!!.rvlist.layoutManager=LinearLayoutManager(activity)
                        binding!!.rvlist.layoutManager
                        binding!!.rvlist.adapter= madapter
                        madapter.notifyDataSetChanged()
                            val decoration= DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                            binding!!.rvlist.addItemDecoration(decoration)
                        },2500)
                    }
                 if(!result.status){
                     binding!!.usersprogressbar.visibility=View.GONE
                     binding!!.rvlist.visibility=View.VISIBLE
                     AppUtils.DisplayMessage(binding!!.detailview, "something went wrong", R.drawable.error)

                 }
                }
            }
         Status.LOADING->{

         }
           Status.ERROR->{
               binding!!.usersprogressbar.visibility=View.GONE
               binding!!.rvlist.visibility=View.VISIBLE
           }
        }
    }
})
    }

}