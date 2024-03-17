package com.example.userinformation.cloth

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R

class Cloth : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cloth)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Welcome To Cloth Shop")
            .setMessage("Have a good day !!")
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

//        val cloth: MutableList<ClothList> = mutableListOf()

        val cloth = arrayListOf<ClothList>()
        cloth.add(ClothList("Jeans", 450.00))
        cloth.add(ClothList("Jeans", 500.00))
        cloth.add(ClothList("Jeans", 240.89))
        cloth.add(ClothList("Jeans", 600.0))
        cloth.add(ClothList("Jeans", 350.0))
        cloth.add(ClothList("Jeans", 450.70))
        cloth.add(ClothList("Jeans", 250.50))
        cloth.add(ClothList("Jeans", 3000.0))
        cloth.add(ClothList("Jeans", 540.0))
        cloth.add(ClothList("Jeans", 300.90))
        cloth.add(ClothList("Jeans", 800.90))
        cloth.add(ClothList("Jeans", 360.80))


        val list = findViewById<ListView>(R.id.listview)


        val listAdapter: ArrayAdapter<ClothList> = ArrayAdapter<ClothList>(
            this, android.R.layout.simple_list_item_1, cloth
        )
          list.adapter=listAdapter


    }
}
