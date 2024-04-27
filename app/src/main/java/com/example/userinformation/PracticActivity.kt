package com.example.userinformation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.databinding.ActivityPracticBinding

class PracticActivity : AppCompatActivity() {
    private lateinit var binding :ActivityPracticBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}