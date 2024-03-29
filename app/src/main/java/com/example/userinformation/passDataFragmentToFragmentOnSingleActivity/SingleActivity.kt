package com.example.userinformation.passDataFragmentToFragmentOnSingleActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivitySingleBinding
import com.example.userinformation.passDataFragmentToFragmentOnSingleActivity.fragmentInterface.PassDataFragmentToFragment

class SingleActivity : AppCompatActivity(), PassDataFragmentToFragment {
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

        // create an object of Bundle

        val bundle = Bundle()

//        set data using argument

//        pass data from firstFragment to SecondFragment

        val fragment2 = SecondFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment2).commit()

    }
}