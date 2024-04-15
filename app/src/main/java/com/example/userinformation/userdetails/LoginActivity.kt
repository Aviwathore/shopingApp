package com.example.userinformation.userdetails

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityLoginBinding
import com.example.userinformation.textbottomsheetdialogbox.CustomTextBottomSheetDialog


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        if (savedUsername!=null && savedPassword!=null){

            binding.editUserName
                .setText(savedUsername)
            binding.editPassword.
            setText(savedPassword)

        }else{
            showLoginScreen()
        }

        binding.loginbtn.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                val editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit()
                editor.putString("username", binding.editUserName.text.toString())
                editor.putString("password", binding.editPassword.text.toString())

                editor.apply()

                val enterUserName= binding.editUserName.text.toString()
                val enterPassword = binding.editPassword.text.toString()
                if (TextUtils.isEmpty(enterUserName)) {
                    binding.editUserName.error = "User name is required"
                    invalidForm()
                    return
                }
                if (TextUtils.isEmpty(enterPassword)) {
                    invalidForm()
                    binding.editPassword.error = "Password is required"
                    return
                } else {
                    startActivity(Intent(this@LoginActivity, DashBoardActivity::class.java))
                }
            }
        })

    }

    private fun showLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onLink(view: View) {
        if (view.id == R.id.link) {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }


    private fun invalidForm() {

        val bottomSheetDialog = CustomTextBottomSheetDialog.newInstance()
        bottomSheetDialog.show(supportFragmentManager, "CustomTextBottomSheetDialog")
    }


}
