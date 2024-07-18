package com.example.userinformation.electronics


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityElectronicsBinding

//private const val BASE_URL ="https://dummyjson.com/"
class ElectronicsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityElectronicsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}
