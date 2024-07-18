package com.example.userinformation.dashboard.productdetails

import com.example.userinformation.dashboard.productdetails.model.Product

interface OnDeleteProductClickListener {

    fun productClick(position: Int, productList : ArrayList<Product>)
}
