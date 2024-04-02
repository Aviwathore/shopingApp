package com.example.userinformation.layout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.databinding.ActivityLinearLayoutBinding

class LinearLayoutActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityLinearLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLinearLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBtn1?.setOnClickListener{

            Toast.makeText(this, "Your Click On Image Button", Toast.LENGTH_SHORT).show()
        }
    }
}