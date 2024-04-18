package com.example.userinformation.dashboard

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.Groceries.GroceriesActivity
import com.example.userinformation.customAdapter.CustomListView
import com.example.userinformation.R
import com.example.userinformation.activityLifeCycle.LifeCycleOFActivity
import com.example.userinformation.beauty.Beauty
import com.example.userinformation.cloth.Cloth
import com.example.userinformation.customViewForRecycleView.CARVActivity
import com.example.userinformation.customdialogbox.CustomDialogBoxActivity
import com.example.userinformation.dashboard.productdetails.AddProductActivity
import com.example.userinformation.dashboard.productdetails.ViewProductsActivity
import com.example.userinformation.dashboard.service.ServiceExample
import com.example.userinformation.dashboard.task.UserInformationActivity
import com.example.userinformation.databinding.ActivityMainBinding
import com.example.userinformation.electronics.ElectronicsActivity
import com.example.userinformation.fragmentToActivity.FragmentToActivity
import com.example.userinformation.home.Home
import com.example.userinformation.pharmacy.Pharmacy
import com.example.userinformation.intent.IntentActivity
import com.example.userinformation.timepicker.TimePickerActivity
import com.example.userinformation.userdetails.LoginActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var topAppBar: MaterialToolbar


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        topAppBar = findViewById(R.id.app_bar)
        val fav = findViewById<ActionMenuItemView>(R.id.favorite)

        val search = findViewById<ActionMenuItemView>(R.id.search)
        search.setOnClickListener{
            Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
        }
        val logOut = findViewById<ActionMenuItemView>(R.id.logout)
        logOut.setOnClickListener{
            Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
                        val editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit()
                        editor.putBoolean("flag", false)
                        editor.apply()

                        startActivity(Intent(this, LoginActivity::class.java))
        }
        fav.setOnClickListener{
            Toast.makeText(this, "Favorite Click!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, UserInformationActivity::class.java))

        Snackbar.make(topAppBar, "Login Successfully", Snackbar.LENGTH_SHORT).setAction("close") {

        }.show()
        topAppBar.setNavigationOnClickListener {

            Toast.makeText(this, "Navigation Click!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, ViewProductsActivity::class.java))

//
//            topAppBar.setOnMenuItemClickListener { item ->
//                when (item.itemId) {
//                    R.id.favorite -> {
//                        Toast.makeText(this, "Favorite Click!", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this, UserInformationActivity::class.java))
//                        true
//                    }
//
//                    R.id.search -> {
//                        Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//
//                    R.id.logout -> {
////                        Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
//                        val editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit()
//                        editor.putBoolean("flag", false)
//                        editor.apply()
//
//                        startActivity(Intent(this, LoginActivity::class.java))
//
//                        true
//                    }
//
//                    else -> {
//                        false
//                    }
//                }


            }

        }

//        val intent = Intent(this, ServiceExample::class.java)
//        ContextCompat.startForegroundService(this, intent)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }


//    private fun onTimeClicker(view: View) {
//        if (view.id== R.id.timerpicker){
//            startActivity(Intent(this, TimePickerActivity::class.java))
//        }
//    }

//    private fun onTimeClicker(timerpicker: Int) {
//        if (timerpicker==R.id.timerpicker) {
//            startActivity(Intent(this, TimePicker::class.java))
//        }
//    }
    fun onCloth(view: View) {
        if (view.id == R.id.btnCloths) {
            startActivity(Intent(this, Cloth::class.java))
        }
    }

    fun onElectronics(view: View) {
        if (view.id == R.id.btn_ele) {
            startActivity(Intent(this, ElectronicsActivity::class.java))
        }
    }

    fun onHome(view: View) {
        if (view.id == R.id.btn_home) {
            startActivity(Intent(this, Home::class.java))
        }
    }

    fun onBeauty(view: View) {
        if (view.id == R.id.btn_beauty) {
            startActivity(Intent(this, Beauty::class.java))
        }
    }

    fun onPharmacy(view: View) {
        if (view.id == R.id.btn_pharmacy) {
            startActivity(Intent(this, Pharmacy::class.java))
        }
    }

    fun onGroceries(view: View) {
        if (view.id == R.id.btn_groceries) {
            startActivity(Intent(this, GroceriesActivity::class.java))
        }
    }

    fun onPickMe(view: View) {
        if (view.id == R.id.amazon) {
            startActivity(Intent(this, TimePickerActivity::class.java))

        }
    }

//    fun onShoes(view: View) {
//        if (view.id==R.id.shoes){
//            startActivity(Intent(this, Shoes::class.java))
//        }
//    }

//    fun onEmployee(view: View) {
//        if (view.id==R.id.Employee){
//            startActivity(Intent(this, Employee::class.java))
//        }
//    }

//    fun onUser(view: View) {
//        if(view.id==R.id.users){
//            startActivity(Intent(this, UserDetails::class.java))
//        }
//    }

//    fun onTextField(view: View) {
//        if(view.id==R.id.textField){
//            startActivity(Intent(this, InputFieldActivity::class.java))
//        }
//    }

    fun onCustomAdaptor(view: View) {
        if (view.id == R.id.customAdaptor) {
            startActivity(Intent(this, CustomListView::class.java))
        }
    }

    fun onProduct(view: View) {
        if (view.id == R.id.products) {
            startActivity(Intent(this, IntentActivity::class.java))
        }
    }

    fun onCARV(view: View) {
        if (view.id == R.id.carv) {
            startActivity(Intent(this, CARVActivity::class.java))
        }
    }

    fun onUserInfo(view: View) {
        if (view.id == R.id.user_info) {
            startActivity(Intent(this, LifeCycleOFActivity::class.java))
        }
    }

    fun onFragmentToActivity(view: View) {
        if (view.id == R.id.fragment) {
            startActivity(Intent(this, FragmentToActivity::class.java))
        }
    }

//   fun onFragmentToFragmentSingleActivity(view: View){
//       if (view.id==R.id.fragment_to_fragment_onSingle_Activity){
//           startActivity(Intent(this, SingleActivity::class.java))
//       }
//   }
//    fun onLayout(view: View){
//        if (view.id==R.id.layout_id){
//            startActivity(Intent(this,LinearLayoutActivity::class.java))
//        }
//    }

    fun onCustomDialog(view: View) {
        if (view.id == R.id.custom_dialog) {
            startActivity(Intent(this, CustomDialogBoxActivity::class.java))
        }
    }


}