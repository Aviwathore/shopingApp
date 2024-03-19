package com.example.userinformation.electronics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityElectronicsBinding
import com.example.userinformation.electronics.recyclearviewlist.model.RecycleItem
import com.example.userinformation.electronics.recyclearviewlist.adapter.ElectronicsAdaptor

class Electronics : AppCompatActivity() {
    private lateinit var binding: ActivityElectronicsBinding
    private lateinit var adaptor: ElectronicsAdaptor

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_electronics)

        val builder=AlertDialog.Builder(this)
        builder.setTitle("Welcome To Electronics Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog=builder.create()
        alertDialog.show()

        val eleList = arrayListOf<RecycleItem>()


        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))
        eleList.add(RecycleItem("Laptop","Latest"))

        val recyclerview = findViewById<RecyclerView>(R.id.recycle_view)
//        recyclerview.setHasFixedSize(true)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val electronicsAdaptor =ElectronicsAdaptor(eleList)

        recyclerview.adapter=electronicsAdaptor

        electronicsAdaptor.notifyDataSetChanged()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}