package com.example.userinformation.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.Groceries.Groceries
import com.example.userinformation.R
import com.example.userinformation.beauty.Beauty
import com.example.userinformation.cloth.Cloth
import com.example.userinformation.databinding.ActivityMainBinding
import com.example.userinformation.electronics.Electronics
import com.example.userinformation.employee.Employee
import com.example.userinformation.home.Home
import com.example.userinformation.pharmacy.Pharmacy
import com.example.userinformation.shoes.Shoes
//import com.example.userinformation.pharmacy.Pharmacy
import com.example.userinformation.timepicker.TimePicker
import com.example.userinformation.user.UserDetails
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class DashBoard : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    private lateinit var topAppBar :MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAppBar= findViewById(R.id.app_bar)

        Snackbar.make(topAppBar,"Login Successfully",Snackbar.LENGTH_SHORT).setAction("close"){

        }.show()
        topAppBar.setNavigationOnClickListener{

            Toast.makeText(this,"Navigation Click!",Toast.LENGTH_SHORT).show()

            topAppBar.setOnMenuItemClickListener{item->
                when(item.itemId){
                    R.id.favorite ->{
                        Toast.makeText(this,"Favorite Click!", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.search ->{
                        Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.timerpicker ->{
                        onTimeClicker(it)
                        true
                    }
                    else -> {false
                    }
                }


            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onTimeClicker(view: View) {
        if (view.id== R.id.timerpicker){
            startActivity(Intent(this, TimePicker::class.java))
        }
    }

//    private fun onTimeClicker(timerpicker: Int) {
//        if (timerpicker==R.id.timerpicker) {
//            startActivity(Intent(this, TimePicker::class.java))
//        }
//    }

    fun onCloth(view: View) {
        if (view.id== R.id.btnCloths) {
            startActivity(Intent(this, Cloth::class.java))
        }
    }
    fun onElectronics(view: View) {
        if (view.id== R.id.btn_ele){
            startActivity(Intent(this, Electronics::class.java))
        }
    }
    fun onHome(view: View) {
        if (view.id== R.id.btn_home){
            startActivity(Intent(this, Home::class.java))
        }
    }
    fun onBeauty(view: View) {
        if (view.id== R.id.btn_beauty){
            startActivity(Intent(this, Beauty::class.java))
        }
    }
    fun onPharmacy(view: View) {
        if (view.id== R.id.btn_pharmacy){
            startActivity(Intent(this, Pharmacy::class.java))
        }
    }
    fun onGroceries(view: View) {
        if (view.id== R.id.btn_groceries){
            startActivity(Intent(this, Groceries::class.java))
        }
    }

    fun onPickMe(view: View) {
        if (view.id== R.id.amazon){
            startActivity(Intent(this, TimePicker::class.java))

        }
    }

    fun onShoes(view: View) {
        if (view.id==R.id.shoes){
            startActivity(Intent(this, Shoes::class.java))
        }
    }

    fun onEmployee(view: View) {
        if (view.id==R.id.Employee){
            startActivity(Intent(this, Employee::class.java))
        }
    }

    fun onUser(view: View) {
        if(view.id==R.id.users){
            startActivity(Intent(this, UserDetails::class.java))
        }
    }

}