package com.example.userinformation.paymentdetails.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.databinding.PaymentItemsDetailsLayoutBinding
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem

class CartProductAdapter(
) :
    RecyclerView.Adapter<CartProductAdapter.ViewHolder>() {

    private var cartProductList: MutableList<ClothItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PaymentItemsDetailsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cartProductList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCartProduct(cartProductList: List<ClothItem>) {
        this.cartProductList = cartProductList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PaymentItemsDetailsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ClothItem) {
            binding.txtProductName.text = product.title

            binding.txtProductQty.text = product.productCount.toString()

            val totalCost = product.price * 83.50
            val productAmount = formatToIndianNumberingSystem(totalCost)
            binding.txtProductCost.text = productAmount

            val totalAmount = product.productCount * totalCost
            binding.txtProductFinalCost.text = formatToIndianNumberingSystem(totalAmount)

        }
    }

    interface OnProductChangedListener {
        fun onProductChangedListener(newTotalPrice: Double, position: Int)
    }
}