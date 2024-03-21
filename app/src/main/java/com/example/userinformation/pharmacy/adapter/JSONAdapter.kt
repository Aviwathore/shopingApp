package com.example.userinformation.pharmacy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R

class JSONAdapter(private val pharmacyList : ArrayList<String>) : RecyclerView.Adapter<JSONAdapter.ViewHolder>() {
    class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {

        val textResponse :TextView

        init {
            textResponse=itemView.findViewById(R.id.text_pharmacy_response)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JSONAdapter.ViewHolder {
        val viewItem =LayoutInflater.from(parent.context).inflate(R.layout.pharmacy_recycle_layout, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: JSONAdapter.ViewHolder, position: Int) {
        val itemPosition = pharmacyList[position]
        holder.textResponse.text=itemPosition.toString()
    }

    override fun getItemCount(): Int {
       return pharmacyList.size
    }
}