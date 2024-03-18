package com.example.userinformation.userdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.dashboard.DashBoard
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityLoginBinding



class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

//        val viewButton : TextView =findViewById(R.id.link)
//
//        viewButton.setOnClickListener{
//            val intent = Intent(this, Registration::class.java)
//            startActivity(intent)
//        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val btnId :MaterialButton=findViewById(R.id.loginbtn)
//
//        btnId.setOnClickListener{
//            Snackbar.make(btnId, "Login Successfully. ", Snackbar.LENGTH_LONG)
//                .setAction("Cancel"){
//
//                }
//                .show()
//        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun onLogin(view: View) {
        if (view.id == R.id.loginbtn) {
            /*
//            editUserName =findViewById(R.id.username)
//            Log.d("onLogin", "Username: ${editUserName.text}")
//            val rootView =findViewById<View>(android.R.id.content)

//            Snackbar.make(onLogin, "Login Successfully. ", Snackbar.LENGTH_LONG)
//                .setAction("Cancel"){
//
//                }
//                .show()

//            startActivity(Intent(this, MainActivity::class.java).putExtra("username",editUserName.text.toString()))
        }

             */

            startActivity(Intent(this, DashBoard::class.java))

        }

    }

    fun onLink(view: View) {
        if (view.id == R.id.link) {
            startActivity(Intent(this, Registration::class.java))
        }
    }
}