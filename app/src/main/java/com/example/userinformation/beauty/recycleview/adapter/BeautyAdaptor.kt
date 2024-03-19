package com.example.userinformation.beauty.recycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.home.recycleviewapi.adapter.HomeAdaptor

class BeautyAdaptor (private val beautyList: ArrayList<String>):RecyclerView.Adapter<BeautyAdaptor.ViewHolder>() {
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val user: TextView

        init {
            user=view.findViewById(R.id.userId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeautyAdaptor.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycle_layout, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeautyAdaptor.ViewHolder, position: Int) {
        val recycle=beautyList[position]

        holder.user.text= recycle.toString()
    }

    override fun getItemCount(): Int {
        return  beautyList.size
    }
}