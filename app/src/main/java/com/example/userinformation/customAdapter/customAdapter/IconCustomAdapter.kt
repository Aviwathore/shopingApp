package com.example.userinformation.customAdapter.customAdapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.userinformation.R
import com.example.userinformation.customAdapter.modal.Icons


class IconCustomAdapter(private val context :Activity, private  var iconList : ArrayList<Icons>): ArrayAdapter<Icons>(context, R.layout.list_view, iconList){

    @SuppressLint("ViewHolder", "InflateParams", "MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater :LayoutInflater =LayoutInflater.from(context)
        val view : View= inflater.inflate(R.layout.list_view, null)

        val name =view.findViewById<TextView>(R.id.name)
        val img = view.findViewById<ImageView>(R.id.img_view)
        val lastMessage =view.findViewById<TextView>(R.id.lastMessage)

        name.text= iconList[position].name
        img.setImageResource(iconList[position].image)
        lastMessage.text = iconList[position].lastMessage


        return view

    }

}