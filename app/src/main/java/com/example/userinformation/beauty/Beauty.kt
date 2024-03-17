package com.example.userinformation.beauty

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

class Beauty : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beauty)
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Welcome To Beauty Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog =builder.create()
        alertDialog.show()

        val beauty = findViewById<ListView>(R.id.beautylist)

        val beautyList = arrayListOf<BeautyList>()

        beautyList.add(BeautyList("Foundation",178.0))
        beautyList.add(BeautyList("Foundation",808.0))
        beautyList.add(BeautyList("Foundation",490.0))
        beautyList.add(BeautyList("Foundation",978.0))
        beautyList.add(BeautyList("Foundation",333.0))
        beautyList.add(BeautyList("Foundation",767.0))
        beautyList.add(BeautyList("Foundation",264.0))
        beautyList.add(BeautyList("Foundation",989.0))
        beautyList.add(BeautyList("Foundation",356.0))
        beautyList.add(BeautyList("Foundation",800.0))
        beautyList.add(BeautyList("Foundation",450.0))

        val listAdapter :ArrayAdapter<BeautyList> =ArrayAdapter<BeautyList>(
            this,android.R.layout.simple_list_item_1, beautyList
        )
        beauty.adapter = listAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}