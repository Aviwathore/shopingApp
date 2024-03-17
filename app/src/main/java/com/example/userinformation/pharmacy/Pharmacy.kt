package com.example.userinformation.pharmacy

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

class Pharmacy : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pharmacy)

        val builder= AlertDialog.Builder(this)
        builder.setTitle("Welcome To Pharmacy Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog =builder.create()
        alertDialog.show()

        val pharmacy =findViewById<ListView>(R.id.pharmacylist)

        val pharmacyList = arrayListOf<PharmacyList>()

        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))
        pharmacyList.add(PharmacyList("Nuclear", 200.00))

        val listAdapter :ArrayAdapter<PharmacyList> = ArrayAdapter<PharmacyList>(

            this, android.R.layout.simple_list_item_1, pharmacyList
        )

        pharmacy.adapter = listAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}