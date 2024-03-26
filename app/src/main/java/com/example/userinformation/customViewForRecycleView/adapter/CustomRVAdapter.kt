package com.example.userinformation.customViewForRecycleView.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.customViewForRecycleView.CARV
import com.example.userinformation.customViewForRecycleView.modal.Data
import com.example.userinformation.customViewForRecycleView.modal.UserResponse

class CustomRVAdapter(private val context: Context, private val customDataList: ArrayList<Data>): RecyclerView.Adapter<CustomRVAdapter.DataViewHolder>
    () {
    class DataViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val id :TextView
        val firstName :TextView
        val lastName :TextView
        val email :TextView
        val avatar :ImageView

        init {
            id =view.findViewById(R.id.carv_id)
            firstName= view.findViewById(R.id.carv_first_name)
            lastName =view.findViewById(R.id.carv_last_name)
            email =view.findViewById(R.id.carv_email)
            avatar = view.findViewById(R.id.avatar_img)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val v =LayoutInflater.from(context).inflate(R.layout.custom_adapter_recycle_view, parent, false)
        return DataViewHolder(v)

    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val userResponse = customDataList[position]
        val userData = userResponse

        holder.id.text=userData.id.toString()
        holder.firstName.text= userData.firstName.toString()
        holder.lastName.text= userData.lastName
        holder.email.text=userData.email

        Glide.with(context).load(userData.avatar).into(holder.avatar)
    }
    override fun getItemCount(): Int {
        return  customDataList.size
    }


}