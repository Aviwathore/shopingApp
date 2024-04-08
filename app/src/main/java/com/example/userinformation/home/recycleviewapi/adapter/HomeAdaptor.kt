package com.example.userinformation.home.recycleviewapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.DialogTitle
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.home.recycleviewapi.api.OnDeleteItemClickListener
import com.example.userinformation.home.recycleviewapi.model.HomeToDo

class HomeAdaptor(private var homeList: ArrayList<HomeToDo>, private var deleteItem: OnDeleteItemClickListener):RecyclerView.Adapter<HomeAdaptor.ViewHolder>() {

//    fun setOnDeleteItemClickListener(deleteItem: OnDeleteItemClickListener){
//        this.deleteItem=deleteItem
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updatedList : ArrayList<HomeToDo>){
        homeList = updatedList
        notifyDataSetChanged()
    }
    class ViewHolder(view : View, private val deleteItem:OnDeleteItemClickListener):RecyclerView.ViewHolder(view) {

        val id :TextView
        val user: TextView
        val title : TextView
        val status: TextView

        val deleteButton : AppCompatButton

        init {
            id=view.findViewById(R.id.id)
            user=view.findViewById(R.id.user)
            title=view.findViewById(R.id.title)
            status=view.findViewById(R.id.completed_status)
            deleteButton=view.findViewById(R.id.img_delete)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdaptor.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycle_layout, parent, false)
        return  ViewHolder(view, deleteItem)  // pass deleteItem to ViewHolder
    }

    override fun onBindViewHolder(holder: HomeAdaptor.ViewHolder, position: Int) {
        val item=homeList[position]

        holder.id.text= item.id.toString()
        holder.user.text=item.userId.toString()
        holder.title.text=item.title
        holder.status.text=item.completed

        holder.deleteButton.setOnClickListener{
            deleteItem.onItemClick(position, homeList)
        }

    }

    override fun getItemCount(): Int {
        return  homeList.size
    }
}