package com.example.userinformation.home.recycleviewapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R

class HomeAdaptor(private val homeList: ArrayList<String>):RecyclerView.Adapter<HomeAdaptor.ViewHolder>() {
    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val user: TextView
//        val id :TextView
//        val title: TextView
//        val completed: TextView

        init {
            user=view.findViewById(R.id.userId)
//            id=view.findViewById(R.id.id)
//            title=view.findViewById(R.id.title)
//            completed=view.findViewById(R.id.completed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdaptor.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycle_layout, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdaptor.ViewHolder, position: Int) {
        val recycle=homeList[position]

        holder.user.text= recycle.toString()
//        holder.id.text= recycle.toString()
//        holder.title.text= recycle.toString()
//        holder.completed.text= recycle.toString()
    }

    override fun getItemCount(): Int {
        return  homeList.size
    }
}