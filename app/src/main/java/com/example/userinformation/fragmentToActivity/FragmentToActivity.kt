package com.example.userinformation.fragmentToActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityFragmentToBinding

class FragmentToActivity : AppCompatActivity() {

    private lateinit var customerEditName :EditText
    private lateinit var  customerEditContact :EditText
    private lateinit var  binding: ActivityFragmentToBinding

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentToBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customerEditName= binding.idCustomerName
        customerEditContact = binding.idCustomerContact

        // declared fragment transaction
        // transfer data
        // create an object of fragment

        val fragmentManager = supportFragmentManager.beginTransaction()
        val myFragment = FragmentOne()


        binding.btnCustomerDataSend.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("cName", customerEditName.text.toString())
            bundle.putString("cContact", customerEditContact.text.toString())

            myFragment.arguments= bundle
            fragmentManager.add(R.id.fragment_layout, myFragment).commit()
        }

        }
}