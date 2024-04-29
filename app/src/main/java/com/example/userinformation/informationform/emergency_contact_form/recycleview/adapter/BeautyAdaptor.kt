package com.example.userinformation.informationform.emergency_contact_form.recycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R

class BeautyAdaptor(private val beautyList: ArrayList<String>,
                    private val onItemClick: (String) -> Unit ):RecyclerView.Adapter<BeautyAdaptor.ViewHolder>() {
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val user: TextView

        init {
            user=view.findViewById(R.id.user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycle_layout, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recycle=beautyList[position]

        holder.user.text= recycle.toString()

        val beautyItem = beautyList[position]
        holder.itemView.setOnClickListener { onItemClick(recycle) }
    }

    override fun getItemCount(): Int {
        return  beautyList.size
    }
}