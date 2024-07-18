package com.example.userinformation.myorders

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem

class ActiveProductAdapter(private val context: Context) :
    RecyclerView.Adapter<ActiveProductAdapter.AddToCartViewHolder>() {

    private var addToCartList: MutableList<ClothItem> = mutableListOf()

    private var dbHelper: ProductDBHelper = ProductDBHelper(context)
    private var price = 0.0
    private var totalPrice = 0.0
    private var formattedNumber = ""

    companion object {
        const val CONVERSION_FACTOR = 83.50
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setCartProduct(addToCartList: List<ClothItem>) {
        this.addToCartList.clear()
        this.addToCartList.addAll(addToCartList)
        notifyDataSetChanged()
    }


    class AddToCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImg: ImageView = itemView.findViewById(R.id.img_product)
        var productTitle: TextView = itemView.findViewById(R.id.txt_product_title)
        var productSize: TextView = itemView.findViewById(R.id.txt_product_selected_size)
        var productPrice: TextView = itemView.findViewById(R.id.txt_product_cost)
//        var productCount: TextView = itemView.findViewById(R.id.txt_count)
        var productQty: TextView = itemView.findViewById(R.id.txt_product_qty_count)
        var productOrderCancel: TextView = itemView.findViewById(R.id.btn_cancel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToCartViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.active_product_layout, parent, false)
        return AddToCartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return addToCartList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AddToCartViewHolder, position: Int) {
        val item = addToCartList[position]

        Glide.with(holder.productImg)
            .load(item.image)
            .into(holder.productImg)

        holder.productTitle.text = item.title
        holder.productSize.text = item.productSize
        holder.productQty.text = item.productCount.toString()
//        holder.productCount.text = item.productCount.toString()
        price = item.price
        totalPrice = (price * CONVERSION_FACTOR)

        formattedNumber = formatToIndianNumberingSystem(totalPrice)
        holder.productPrice.text = formattedNumber

        holder.productOrderCancel.setOnClickListener {
            dbHelper.updateOrderConfirmStatus(0,0,"yes",0,0,1)
        }

    }

}