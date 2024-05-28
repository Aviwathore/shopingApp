package com.example.userinformation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding :ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtNavigation.setOnClickListener{

            val navController = binding.txtNavigation.findNavController()
            navController.navigate(R.id.firstFragment)
        }
    }
}