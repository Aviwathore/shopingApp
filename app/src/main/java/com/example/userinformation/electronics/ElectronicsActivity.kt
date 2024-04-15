package com.example.userinformation.electronics


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityElectronicsBinding

//private const val BASE_URL ="https://dummyjson.com/"
class ElectronicsActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityElectronicsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_electronics)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        setupActionBarWithNavController(navController)

    }

}