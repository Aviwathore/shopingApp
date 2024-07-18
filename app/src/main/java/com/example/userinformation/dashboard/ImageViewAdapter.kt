package com.example.userinformation.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.userinformation.R

class ImageViewAdapter(
    private val imageView: List<Int>,
    private val texts: List<String>,
    private val description: List<String>,
    private val viewPager2: ViewPager2,
    private val dotLayout: LinearLayout
) :
    RecyclerView.Adapter<ImageViewAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewId: ImageView = itemView.findViewById(R.id.imageView1)
        val textId: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_container, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageView.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageViewId.setImageResource(imageView[position])
        holder.textId.text = texts[position]
        holder.textView2.text = description[position]

//        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
//        layoutParams.marginStart = if (position == 0) 0 else this.itemCount // Set margin for the first item
//        layoutParams.marginEnd = if (position == itemCount - 1) 0 else resources.getDimensionPixelSize(R.dimen.card_margin) // Set margin for the last item
//        holder.itemView.layoutParams = layoutParams

        if (position == imageView.size - 1) {
            viewPager2.postDelayed(runnable, 3000)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        if (viewPager2.currentItem < itemCount - 1) {
            viewPager2.currentItem += 1
        } else {
            viewPager2.setCurrentItem(0, false)
        }
        notifyDataSetChanged()
    }
    fun updateDotIndicator(position: Int) {
        for (i in 0 until dotLayout.childCount) {
            val dot = dotLayout.getChildAt(i) as ImageView
            dot.setImageResource(if (i == position) R.drawable.selected_dot else R.drawable.unselected_dot)
        }
    }
}