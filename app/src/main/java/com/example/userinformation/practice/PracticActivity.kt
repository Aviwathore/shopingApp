//package com.example.userinformation.practice
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.os.Handler
//import android.widget.ImageView
//import android.widget.LinearLayout
//import androidx.appcompat.app.AppCompatActivity
//import androidx.viewpager2.widget.ViewPager2
//import com.example.userinformation.R
//import com.example.userinformation.databinding.ActivityPracticBinding
//
//class PracticActivity : AppCompatActivity() {
//    private lateinit var binding :ActivityPracticBinding
//    private lateinit var viewPager2: ViewPager2
//    private lateinit var adapter: ImageAdapter
//    private lateinit var dotLayout :LinearLayout
////    private lateinit var listImage :ArrayList<Int>
//
//    private val handler = Handler()
//    private val scrollRunnable = object : Runnable {
//        override fun run() {
//            val currentItem = viewPager2.currentItem
//
//            viewPager2.setCurrentItem(currentItem + 1, true)
//            handler.postDelayed(this, 3000)
//        }
//
//    }
//    @SuppressLint("UseCompatLoadingForDrawables")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityPracticBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        viewPager2 = binding.viewPager
//        dotLayout = binding.dotLayout
//
//        adapter = ImageAdapter(dotLayout, viewPager2)
//
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                updatesDot(position)
//            }
//
//        })
//
//        handler.postDelayed(scrollRunnable, 3000)
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        handler.removeCallbacks(scrollRunnable)
//    }
//
//    private fun updatesDot(position: Int) {
//        for (i in 0 until dotLayout.childCount) {
//            val dot = dotLayout.getChildAt(i)as ImageView
//            dot.setImageResource(
//                if (i == position % adapter.itemCount)
//                    R.drawable.china
//                else
//                    R.drawable.malaysia
//            )
//        }
//    }
//
//
//}