package com.example.userinformation.customAdapter.singlePage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityNewCustomListViewBinding

class NewCustomListView : AppCompatActivity() {
    private lateinit var binding: ActivityNewCustomListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewCustomListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name =intent.getStringExtra("name")
        val img = intent.getIntExtra("image", R.drawable.cloth)
        val message =intent.getStringExtra("lastmessage")

        binding.newName.text= name
        binding.profileImg.setImageResource(img)
        binding.newMessage.text=message

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}