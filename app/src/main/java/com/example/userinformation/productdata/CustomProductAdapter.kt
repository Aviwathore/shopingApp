package com.example.userinformation.productdata

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.userinformation.R

class CustomProductAdapter(private  val context :Activity, private var productList: ArrayList<ProductData>) : ArrayAdapter<ProductData>(context, R.layout.list_view, productList) {

    @SuppressLint("ViewHolder", "InflateParams", "MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater:LayoutInflater= LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_view, null)

        val pName =view.findViewById<TextView>(R.id.product1)
        val pPrice = view.findViewById<TextView>(R.id.product2)
        val pImg = view.findViewById<TextView>(R.id.img_product)

        pName.text= productList[position].productName
        pPrice.text=productList[position].productPrice.toString()

        return super.getView(position, convertView, parent)
    }
}