package com.example.userinformation.dashboard.task.confirmbottomsheetdialog.successfulStoreInfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToHome.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
        }
    }
}