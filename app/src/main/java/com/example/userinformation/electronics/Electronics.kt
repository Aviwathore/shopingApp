package com.example.userinformation.electronics

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

class Electronics : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_electronics)

        val builder=AlertDialog.Builder(this)
        builder.setTitle("Welcome To Electronics Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog=builder.create()
        alertDialog.show()

        val ele =findViewById<ListView>(R.id.electronicslist)

        val eleList = arrayListOf<ElectronicsList>()

        eleList.add(ElectronicsList("Laptop", 28000.00))
        eleList.add(ElectronicsList("Laptop", 10000.90))
        eleList.add(ElectronicsList("Laptop", 450000.00))
        eleList.add(ElectronicsList("Laptop",20000.00))
        eleList.add(ElectronicsList("Laptop", 20500.0))
        eleList.add(ElectronicsList("Laptop", 23000.80))
        eleList.add(ElectronicsList("Laptop", 56000.90))
        eleList.add(ElectronicsList("Laptop", 45000.90))
        eleList.add(ElectronicsList("Laptop", 4000.0))
        eleList.add(ElectronicsList("Laptop", 15000.90))
        eleList.add(ElectronicsList("Laptop", 890000.90))
        eleList.add(ElectronicsList("Laptop", 200.00))

        val listAdapter  :ArrayAdapter<ElectronicsList> =ArrayAdapter<ElectronicsList>(
            this, android.R.layout.simple_list_item_1, eleList

        )

        ele.adapter =listAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}