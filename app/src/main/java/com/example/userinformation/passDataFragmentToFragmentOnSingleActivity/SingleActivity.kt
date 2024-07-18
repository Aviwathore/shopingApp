package com.example.userinformation.passDataFragmentToFragmentOnSingleActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivitySingleBinding
import com.example.userinformation.passDataFragmentToFragmentOnSingleActivity.fragmentInterface.PassDataFragmentToFragmentActivity

class SingleActivity : AppCompatActivity(), PassDataFragmentToFragmentActivity {
    private lateinit var binding: ActivitySingleBinding
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // create a fragment object


        val fragment1 =FirstFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

    }

    override fun passData(data: String) {

        Log.d("dddddddddddddd", "yess")
        // create an object of Bundle

        val bundle = Bundle()

//        set data using argument
        bundle.putString("textMessage", data)
//        pass data from firstFragment to SecondFragment

        val fragment2 = SecondFragment()
        fragment2.arguments=bundle
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment2).addToBackStack(null).commit()
        Log.d("Pass", "data")

    }

    interface PassDataFragmentToFragmentActivity {
        fun passData(data: String)
    }
}