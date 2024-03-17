package com.example.userinformation.userdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {

    private lateinit var binding :ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

//                val materialButton : MaterialButton =findViewById(R.id.signup)
//
//        materialButton.setOnClickListener{
//            val  intent = Intent(this, Login::class.java)
//            startActivity(intent)
//        }

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val btnId :MaterialButton=findViewById(R.id.signup)
//
//        btnId.setOnClickListener{
//
//            Snackbar.make(btnId, "Registration Successfully. ", Snackbar.LENGTH_LONG)
//                .setAction("Cancel"){
//
//                }.show()
//        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onRegistration(view: View) {
        if (view.id== R.id.signup)
        {
            startActivity(Intent(this, Login::class.java))
        }
    }
}