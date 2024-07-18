package com.example.userinformation.userdetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityRegistrationBinding
import com.example.userinformation.databinding.BottomSheetContainerBinding
import com.example.userinformation.textbottomsheetdialogbox.CustomTextBottomSheetDialog
import com.example.userinformation.userdetails.sharedPreference.DataConformationActivity


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var bottomSheetContainerBinding: BottomSheetContainerBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // previous data present as it is
        val editor = getSharedPreferences("Details", MODE_PRIVATE)
        binding.idSignUpName.setText(editor.getString("name", null))
        binding.idEmail.setText(editor.getString("email", null))
        binding.contactId.setText(editor.getString("contact", null))

        emailFocusListener()
        nameFocusListener()
        passwordFocusListener()

        phoneFocusListener()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun emailFocusListener() {
        binding.idEmail.setOnFocusChangeListener { _, focused ->

            if (!focused) {
                binding.editEmail.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {

        val emailText = binding.idEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun nameFocusListener() {
        binding.idSignUpName.setOnFocusChangeListener { _, focused ->

            if (!focused) {
                binding.editName.helperText = validName()
            }
        }
    }

    private fun validName(): String? {

        val nameText = binding.idSignUpName.text.toString()

//        if (nameText.matches(".*[A-Z].*".toRegex()) || nameText.matches(".*[a-z].*".toRegex())){
//            return "Only Contains Character "
//        }
        if (nameText.length < 3) {
            return "Maximum 5 Character"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.idSignUpPassword.setOnFocusChangeListener { _, focused ->

            if (!focused) {
                binding.editPassword.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passText = binding.idSignUpPassword.text.toString()

        if (passText.length<8){
            return "Password must be at least 8 character long"
        }
        if (!Regex("[A-Z]").containsMatchIn(passText)){
            return "Password must contain at least one uppercase letter"
        }
        if (!Regex("[a-z]").containsMatchIn(passText)){
            return "Password must contain at least one lowercase letter"
        }
        if (!Regex("[@!#$%^&*_+=?/|:]").containsMatchIn(passText)){
            return "Password must contain at least one special letter"
        }
        return null
    }

    private fun phoneFocusListener() {
        binding.contactId.setOnFocusChangeListener { _, focused ->

            if (!focused) {
                binding.editContact.helperText = validContact()
            }
        }
    }

    private fun validContact(): String? {

        val contactText = binding.contactId.text.toString()

        if (contactText.length != 10) {

            return "Must be Contain 10 Number"
        }
        return null
    }

    fun onRegistration(view: View) {


        val validName = binding.editName.helperText == null
        val validEmail = binding.editEmail.helperText == null
        val validContact = binding.editContact.helperText == null
        val validPassword = binding.editPassword.helperText == null

        if (view.id == R.id.btn_signup) {
            // create a shared preference for write a data
            val editor = getSharedPreferences(
                "Details",
                MODE_PRIVATE
            ).edit()   // getSharedPreferences : use for access data throwout application
            editor.putString("name", binding.idSignUpName.text.toString())
            editor.putString("email", binding.idEmail.text.toString())
            editor.putString("contact", binding.contactId.text.toString())
            editor.putString("password", binding.idSignUpPassword.text.toString())
            editor.apply()

            if (true){

                if (validName && validEmail && validContact && validPassword) {
                    startActivity(Intent(this, DataConformationActivity::class.java))
                } else {
                    invalidForm()
                }
            }else{
                invalidForm()
            }

        }
    }

    private fun invalidForm() {

        val bottomSheetDialog = CustomTextBottomSheetDialog.newInstance()
        bottomSheetDialog.show(supportFragmentManager, "CustomTextBottomSheetDialog")
    }

}