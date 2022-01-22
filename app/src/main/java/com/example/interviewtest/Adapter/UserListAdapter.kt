package com.example.interviewtest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewtest.Interfaces.ItemClickListener
import com.example.interviewtest.Models.DataX
import com.example.interviewtest.databinding.UsersLayoutBinding

class UserListAdapter(val context: Context,var items:List<DataX>,val listener: ItemClickListener):RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=UsersLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
    return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val datas=items[position]
        with(holder){
            binding.createdat.text=datas.createdAt
            binding.email.text=datas.email
            binding.exp.text=datas.experience
            binding.fname.text=datas.fname
            binding.lname.text=datas.lname
            binding.phone.text=datas.phone
            itemView.setOnClickListener {
                listener.ItemCLick(datas.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class MyViewHolder(val binding: UsersLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

}