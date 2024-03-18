package com.example.userinformation.Groceries

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

class Groceries : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_groceries)

        val builder=AlertDialog.Builder(this)
        builder.setTitle("Welcome To Groceries Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog=builder.create()
        alertDialog.show()

        val grow =findViewById<ListView>(R.id.grocerieslist)

        val groceriesList = arrayListOf<GroceriesList>()

        groceriesList.add(GroceriesList("Fruits", 789.00))
        groceriesList.add(GroceriesList("Fruits", 999.00))
        groceriesList.add(GroceriesList("Fruits", 456.00))
        groceriesList.add(GroceriesList("Fruits", 566.00))
        groceriesList.add(GroceriesList("Fruits", 134.00))
        groceriesList.add(GroceriesList("Fruits", 564.00))
        groceriesList.add(GroceriesList("Fruits", 789.00))
        groceriesList.add(GroceriesList("Fruits", 100.00))
        groceriesList.add(GroceriesList("Fruits", 500.00))
        groceriesList.add(GroceriesList("Fruits", 890.00))
        groceriesList.add(GroceriesList("Fruits", 300.00))


        val listAdapter :ArrayAdapter<GroceriesList> =ArrayAdapter<GroceriesList>(
            this, android.R.layout.simple_list_item_1, groceriesList
        )

        grow.adapter= listAdapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}