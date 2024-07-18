//package com.example.userinformation.practice
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.LinearLayout
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import com.example.userinformation.R
//
//class ImageAdapter(private val dotLayout: ArrayList<Int>, private val viewPager2: ViewPager2):RecyclerView.Adapter<ImageAdapter.ImageHolder>() {
//    private  val imageView = intArrayOf(
//        R.drawable.home,
//        R.drawable.cloth
//    )
//
//    private  val dotView = mutableListOf<ImageView>()
//
//    class ImageHolder(itemView :View):RecyclerView.ViewHolder(itemView) {
//        val imageViewId :ImageView = itemView.findViewById(R.id.imageView1)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
//       val view = LayoutInflater.from(parent.context).inflate(R.layout.image_container, parent, false)
//        return ImageHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//       return  imageView.size
//    }
//
//    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
//        holder.imageViewId.setImageResource(imageView[position])
//
////        if (position== listView.size-1){
////
////            viewPager2.post(runnable)
////        }
//
//        setUpDots(position)
//    }
//
//    private fun setUpDots(position: Int) {
//
//        dotView.clear()
//        dotLayout.removeAllViews()
//
//        for (i in imageView.indices) {
//            val dot = ImageView(dotLayout.context)
//            val params = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            ).apply {
//                setMargins(8, 0, 8, 0)
//            }
//            dot.layoutParams = params
//            dot.setImageResource(if (i == position) R.drawable.china else R.drawable.malaysia)
//            dotView.add(dot)
//            dotLayout.addView(dot)
//        }
//    }
//
////    @SuppressLint("NotifyDataSetChanged")
////    private  val runnable = Runnable{
////        listView.addAll(listView)
////        notifyDataSetChanged()
////    }
//    fun setCurrentItem (position: Int){
//        viewPager2.currentItem = position
//    }
//}