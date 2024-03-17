package com.example.userinformation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R

class Home : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Welcome To Home Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog =builder.create()
        alertDialog.show()

        val home =findViewById<ListView>(R.id.homelist)

        val homeList =ArrayList<HomeList>()

        homeList.add(HomeList("Cottage", 38000.00))
        homeList.add(HomeList("Cottage", 60000.00))
        homeList.add(HomeList("Cottage", 340.8))
        homeList.add(HomeList("Cottage", 18900.9))
        homeList.add(HomeList("Cottage", 4500.90))
        homeList.add(HomeList("Cottage", 7800.0))
        homeList.add(HomeList("Cottage", 50000.0))
        homeList.add(HomeList("Cottage", 3600.0))
        homeList.add(HomeList("Cottage", 7800.0))
        homeList.add(HomeList("Cottage", 70000.0))
        homeList.add(HomeList("Cottage", 8900.0))
        homeList.add(HomeList("Cottage", 9800.0))


        val listAdapter :ArrayAdapter<HomeList> =ArrayAdapter<HomeList>(
            this, android.R.layout.simple_list_item_1, homeList
        )
        home.adapter= listAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}