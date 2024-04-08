package com.example.userinformation.userdetails.sharedPreference

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityDataConformationBinding
import com.example.userinformation.databinding.ActivityRegistrationBinding
import com.example.userinformation.userdetails.LoginActivity
import com.example.userinformation.userdetails.RegistrationActivity

class DataConformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataConformationBinding
    private lateinit var registrationActivity: ActivityRegistrationBinding
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataConformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editor = getSharedPreferences("Details", MODE_PRIVATE)
//        registrationActivity.idSignUpName.setText(editor.getString("name",null))
//        registrationActivity.idEmail.setText(editor.getString("email", null))
//        registrationActivity.contactId.setText(editor.getString("contact", null))
//        registrationActivity.idSignUpPassword.setText(editor.getString("password", null))

        val manager = PreferenceManager.getDefaultSharedPreferences(this)
        listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key.equals("change_UI")) {
                if (manager.getBoolean("change_UI", false)) {
                    binding.main.setBackgroundColor(Color.BLACK)
                    binding.txtConfirmData.setTextColor(Color.WHITE)
                    binding.imgSetting.setBackgroundColor(Color.WHITE)
                }
                else {
                    binding.main.setBackgroundColor(Color.WHITE)
                }
            }
        }
        manager.registerOnSharedPreferenceChangeListener(listener)

        binding.txtConfirmData.setText(
            "Name: ${editor.getString("name", null)}\n\n\n" +
                    "Email: ${editor.getString("email", null)}\n\n\n" +
                    "Contact: ${editor.getString("contact", null)}\n\n\n" +
                    "Password: ${editor.getString("password", null)}\n\n"
        )

        binding.btnConfirm.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.imgSetting.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}