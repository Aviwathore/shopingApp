package com.example.userinformation.addtocart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem

class AddToCartAdapter(
    private val context: Context, private val countChangedListener:
    OnProductCountChangedListener
) :
    RecyclerView.Adapter<AddToCartAdapter.AddToCartViewHolder>() {

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
        var productCountIncrement: ImageView = itemView.findViewById(R.id.img_increment_count)
        var productCountDecrement: ImageView = itemView.findViewById(R.id.img_decrement_count)
        var productCount: TextView = itemView.findViewById(R.id.txt_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToCartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.add_to_cart_layout, parent, false)
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
        holder.productCount.text = item.productCount.toString()
        price = item.price
        totalPrice = (price * CONVERSION_FACTOR)

        formattedNumber = formatToIndianNumberingSystem(totalPrice)
        holder.productPrice.text = formattedNumber

        holder.productCountIncrement.setOnClickListener {
            val currentCount = holder.productCount.text.toString().toInt()

            val newCount = currentCount + 1

            holder.productCount.text = newCount.toString()
            addToCartList[position].productCount = newCount

            val currentStockCount = dbHelper.getStockCount(item.id)
            val currentPrice = totalPrice

            if (newCount <= currentStockCount) {
                dbHelper.updateStockCount(item.id, currentStockCount - 1)

                dbHelper.updateAddToCartCount(item.id, newCount)

                val newTotalPrice = newCount * currentPrice
                countChangedListener.onProductCountChanged(newTotalPrice, position)

            } else {
                Toast.makeText(context, "Not enough stock available", Toast.LENGTH_SHORT).show()
            }
        }

        holder.productCountDecrement.setOnClickListener {
            val currentCountProduct = holder.productCount.text.toString().toInt()
            val newCountProduct = currentCountProduct - 1

            holder.productCount.text = newCountProduct.toString()

            addToCartList[position].productCount = newCountProduct
            val currentPrice = totalPrice


            if (currentCountProduct > 1) {
                dbHelper.updateAddToCartCount(item.id, newCountProduct)
                val newTotalPrice = newCountProduct * currentPrice
                countChangedListener.onProductCountChanged(newTotalPrice, position)

            }else if (currentCountProduct == 1) {

                dbHelper.updateAddToCartStatus(item.id, 0)
                dbHelper.updateAddToCartCount(item.id, newCountProduct)
                addToCartList.removeAt(position)

                notifyDataSetChanged()
                val newTotalPrice = newCountProduct * currentPrice
                countChangedListener.onProductCountChanged(newTotalPrice, position)

            }
        }
    }


    interface OnProductCountChangedListener {
        fun onProductCountChanged(newTotalPrice: Double, position: Int)

    }

}