package com.example.userinformation.dashboard.productdetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.dashboard.productdetails.dbhelper.ProductDB
import com.example.userinformation.dashboard.productdetails.model.Product
import com.example.userinformation.databinding.ActivityViewProductsBinding

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList = ArrayList<Product>()
    private var deleteItemClickListener :OnDeleteProductClickListener?=null

    private var updateProductClickListener : OnUpdateProductClickListener?=null

    fun setUpdateProductClickListener(pListener: OnUpdateProductClickListener){
        updateProductClickListener = pListener
    }
    interface OnUpdateProductClickListener{
        fun updateProductClick(product: Product)
    }

    fun setOnDeleteProductClickListener(listener:OnDeleteProductClickListener){


        deleteItemClickListener=listener
    }
    class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var id: TextView = item.findViewById<TextView>(R.id.txt_pro_id)
        var name: TextView = item.findViewById<TextView>(R.id.txt_pro_name)
        var category: TextView = item.findViewById<TextView>(R.id.txt_pro_category)
        val removeBtn: ImageView? = item.findViewById<ImageView>(R.id.img_btn_delete_product)
        val updateBtn : ImageView? = item.findViewById<ImageView>(R.id.img_btn_update_product)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product_details, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {

        val product = productList[position]
        holder.id.text = product.productId.toString()
        holder.name.text = product.productName.toString()
        holder.category.text = product.productCategory.toString()

        holder.removeBtn?.setOnClickListener{
            deleteItemClickListener?.productClick(position, productList)
        }

        holder.updateBtn?.setOnClickListener{
            updateProductClickListener?.updateProductClick(product)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProduct(product: ArrayList<Product>) {
        productList = product
        notifyDataSetChanged()
    }


}


class ViewProductsActivity : AppCompatActivity() ,OnDeleteProductClickListener, ProductAdapter.OnUpdateProductClickListener{
    private lateinit var binding: ActivityViewProductsBinding
    private lateinit var dbHelper: ProductDB
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add product activity
        binding.productFloatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        recyclerView = findViewById(R.id.product_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrievedAdapter = recyclerView.adapter

        if (retrievedAdapter != null && retrievedAdapter is ProductAdapter) {
            adapter = retrievedAdapter
        } else {
            adapter = ProductAdapter()
            recyclerView.adapter = adapter
        }


        dbHelper = ProductDB(this)
        val productList = ArrayList<Product>()

        dbHelper.getAllProducts(this, productList)
        adapter.setProduct(productList)

        adapter.notifyDataSetChanged()
        adapter.setOnDeleteProductClickListener(this)

        adapter.setUpdateProductClickListener(this)

    }

    override fun productClick(position: Int, productList: ArrayList<Product>) {
        val deletedProduct = productList.removeAt(position)
        // update recyclerview
        adapter.setProduct(productList)
        // delete from database
        dbHelper.deleteProduct(this,deletedProduct)
        Log.d("REMOVE", "deleted from database")
        finish()
    }


    override fun updateProductClick(product: Product) {


        val intent = Intent(this, UpdateProductActivity::class.java)

        intent.putExtra("id", product.productId)
        intent.putExtra("name", product.productName)
        intent.putExtra("category", product.productCategory)

        startActivity(intent)
        finish()
    }
}