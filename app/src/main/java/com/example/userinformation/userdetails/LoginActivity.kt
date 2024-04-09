package com.example.userinformation.userdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userNameFocusListener()
        passwordFocusListener()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun passwordFocusListener() {
        binding.editPassword.setOnFocusChangeListener() { _, focused ->

            if (!focused) {
                binding.containerPassword.helperText = validPassword()
            }

        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.editPassword.text.toString()

//        if (passwordText.length <8){
//            return "Password length must be greater or equal to 8."
//        }
//        if (!Regex("[A-Z]").containsMatchIn(passwordText)){
//            return "Password contain at list 1 upper case letter."
//        }
//        if (!Regex("[a-z]").containsMatchIn(passwordText)){
//            return "Password contain at list 1 lower letter."
//        }
//        if (!Regex("[0-9]").containsMatchIn(passwordText)){
//            return "Password contain at list 1 number."
//        }
//        if (!Regex("[!@#$%^&*+=|/?]").containsMatchIn(passwordText)){
//            return "Password contain at list 1 special character"
//        }

        if (passwordText.length < 8) {
            return "Password must be at least 8 character long"
        }
        if (!Regex("[A-Z]").containsMatchIn(passwordText)) {
            return "Password must contain at least one uppercase letter"
        }
        if (!Regex("[a-z]").containsMatchIn(passwordText)) {
            return "Password must contain at least one lowercase letter"
        }
        if (!Regex("[@!#$%^&*_+=?/|:]").containsMatchIn(passwordText)) {
            return "Password must contain at least one special letter"
        }
        if (!Regex("[0-9]").containsMatchIn(passwordText)){
            return "Password contain at list 1 number."
        }
        return null
    }


    private fun userNameFocusListener() {
        binding.editUserName.setOnFocusChangeListener { _, focused ->

            if (!focused) {
                binding.containerUserName.helperText = validUserName()
            }
        }

    }

    private fun validUserName(): String? {

        val usernameText = binding.editUserName.text.toString()

        if (usernameText.length < 3) {
            return "Username contains at list 3 character."
        }
        return null

    }


    fun onLink(view: View) {
        if (view.id == R.id.link) {
            startActivity(Intent(this, RegistrationActivity::class.java))
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

            startActivity(Intent(this, DashBoardActivity::class.java))

        }

    }


}
