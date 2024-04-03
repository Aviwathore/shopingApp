package com.example.userinformation.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityIntentBinding

class IntentActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityIntentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenWebpage.setOnClickListener{

            val intent = Intent( Intent.ACTION_VIEW, Uri.parse("https://kotlinlang.org/docs/extensions.html#declaring-extensions-as-members"))
            startActivity(intent)
        }

        binding.btnOpenCall.setOnClickListener{
           val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: +1234567890")
            startActivity(intent)
        }

        binding.btnOpenCamera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
        binding.btnSendText.setOnClickListener{
            val text = binding.edtIntentText.text.toString()

            val intent = Intent(Intent.ACTION_SEND)
            intent.type= "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,text)

            startActivity(Intent.createChooser(intent, "Share Via"))
        }
    }
}