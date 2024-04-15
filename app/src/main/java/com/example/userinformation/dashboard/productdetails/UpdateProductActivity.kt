package com.example.userinformation.dashboard.productdetails

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.dashboard.productdetails.dbhelper.ProductDB
import com.example.userinformation.databinding.ActivityUpdateProductBinding

class UpdateProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProductBinding
    private lateinit var dbHelper: ProductDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra("id", 0)
        val productName = intent.getStringExtra("name")
        val productCategory = intent.getStringExtra("category")

        binding.editUpdateId.setText(productId.toString())
        binding.editUpdateName.setText(productName.toString())
        binding.editUpdateCategory.setText(productCategory.toString())

        // disable
        binding.editUpdateId.isClickable = false
        binding.editUpdateId.isEnabled = false

        binding.btnProductUpdate.setOnClickListener {
            dbHelper = ProductDB(this)
            val updateName = binding.editUpdateName.text.toString()
            val updateCategory = binding.editUpdateCategory.text.toString()

            val dataUpdate = dbHelper.updateProduct(this, productId, updateName, updateCategory)

            if (dataUpdate > 0) {
                Toast.makeText(this, updateName + " added to database", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ViewProductsActivity::class.java))
            } else {
                Toast.makeText(
                    this,
                    updateName + " Failed to add data in database",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            finish()

        }
    }

}