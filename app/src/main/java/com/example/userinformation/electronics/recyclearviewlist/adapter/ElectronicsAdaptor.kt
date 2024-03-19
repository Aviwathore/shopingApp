package com.example.userinformation.electronics.recyclearviewlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.electronics.recyclearviewlist.model.RecycleItem

class ElectronicsAdaptor(private val listItem: List<RecycleItem>): RecyclerView.Adapter<ElectronicsAdaptor.ViewHolder>() {
    private lateinit var list:List<RecycleItem>
    class ViewHolder(view :View): RecyclerView.ViewHolder(view) {
        val text1:TextView
        val text2 :TextView

        // Define click listener for the ViewHolder's View
        init {
            text1=view.findViewById(R.id.heading)
            text2=view.findViewById(R.id.desc)
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectronicsAdaptor.ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recycle_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElectronicsAdaptor.ViewHolder, position: Int) {
        val recycleItemPosition = listItem[position]
        holder.text1.text=recycleItemPosition.heading
        holder.text2.text=recycleItemPosition.description
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


}