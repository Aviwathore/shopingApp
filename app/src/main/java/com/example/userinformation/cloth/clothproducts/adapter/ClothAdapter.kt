package com.example.userinformation.cloth.clothproducts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem

class ClothAdapter(private var listener: OnItemClickListener, private val context: Context) :
    RecyclerView.Adapter<ClothAdapter.ClothViewHolder>() {
    private var clothList = emptyList<ClothItem>()

    companion object {
        const val CONVERSION_FACTOR = 83.50
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setClothItem(clothList: List<ClothItem>) {
        this.clothList = clothList
        notifyDataSetChanged()

    }

    class ClothViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var clothImage: ImageView = itemView.findViewById(R.id.img_cloth_item)
        var clothName: TextView = itemView.findViewById(R.id.txt_cloth_name)
        var clothRating: TextView = itemView.findViewById(R.id.txt_cloth_ratting)
        var clothPrice: TextView = itemView.findViewById(R.id.txt_cloth_price)
        var like: FrameLayout = itemView.findViewById(R.id.fl_like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)

        return ClothViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clothList.size
    }

    @SuppressLint("NotifyDataSetChanged", "Recycle")
    override fun onBindViewHolder(holder: ClothViewHolder, position: Int) {
        val item = clothList[position]

        Glide.with(holder.clothImage.context)
            .load(item.image)
            .into(holder.clothImage)

        holder.clothName.text = item.title
        val price = item.price
        val totalPrice = price * CONVERSION_FACTOR

        val formattedNumber = formatToIndianNumberingSystem(totalPrice.toDouble())
        holder.clothPrice.text = formattedNumber

        holder.clothRating.text = item.rating.rate.toString()
        holder.like.setBackgroundResource(if (item.is_fav == 1) R.drawable.heart_pink else R.drawable.heart_white)


        holder.like.setOnClickListener {

            listener.addToFavClickListener(item, position)
        }

        holder.clothImage.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: " + item.title)
            listener.onClothItemClickListener(item, position)
        }

    }


    interface OnItemClickListener {
        fun addToFavClickListener(item: ClothItem, position: Int)
        fun onClothItemClickListener(item: ClothItem, position: Int)
    }

}