package com.example.userinformation.customdialogbox

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R

class CustomDialogBoxActivity : AppCompatActivity() {
    private var fragment = MyFragment.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_dialog_box)

//        val sm = supportFragmentManager.beginTransaction()
//        val myFragment = MyFragment()
//
//        myFragment.show(sm, "Custom Dialog Box Fragment")
        fragment.setSelectedOption(-1)          // Reset Selected Option
        fragment.showDialog(supportFragmentManager, "mydialogbox")

//        fragment.dismissDialog()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStop() {
        super.onStop()

        fragment.dismissDialog()
    }
}