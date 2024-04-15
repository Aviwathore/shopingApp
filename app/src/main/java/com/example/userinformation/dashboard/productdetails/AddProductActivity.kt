package com.example.userinformation.dashboard.productdetails

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.dashboard.productdetails.dbhelper.ProductDB
import com.example.userinformation.databinding.ActivityAddProductBinding


class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var dbHelper: ProductDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSaveProduct.setOnClickListener {

            dbHelper = ProductDB(this)
            val name = binding.editProductName.text.toString()
            val category = binding.editProductCategory.text.toString()

            val saveData = dbHelper.insertProduct(this, name, category)

            if (saveData != null) {
                Toast.makeText(this, name + " added to database", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ViewProductsActivity::class.java))
            } else {
                Toast.makeText(this, name + " Failed to add data in database", Toast.LENGTH_SHORT)
                    .show()
            }


            binding.editProductName.text?.clear()
            binding.editProductCategory.text?.clear()


        }
    }
}