package com.example.userinformation.customViewForRecycleView.fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityCustomRecyclerViewBinding

class CustomRecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val first_name= intent.getStringExtra("firstName")
        val last_name = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val avatar = intent.getIntExtra("avatar",0)


    }
}