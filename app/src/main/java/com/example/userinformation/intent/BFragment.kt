package com.example.userinformation.intent

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.cloth.clothproducts.adapter.ClothAdapter
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.model.ClothItem


class BFragment : Fragment(),ClothAdapter.OnItemClickListener  {

    private lateinit var adapter: ClothAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: ProductDBHelper
    private var clothItemList: List<ClothItem> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_b, container, false)
        adapter = context?.let { ClothAdapter(this, it) }!!
        recyclerView = view.findViewById(R.id.rv_cloth)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        fetchClothItems()

        return view

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchClothItems() {
        dbHelper = ProductDBHelper(requireContext())
        clothItemList = dbHelper.getAllClothItems()
        val filterItem =
            clothItemList.filter { it.category == "men's clothing" || it.category == "women's clothing" }

        adapter.setClothItem(filterItem)

    }

    override fun addToFavClickListener(item: ClothItem, position: Int) {

    }

    override fun onClothItemClickListener(item: ClothItem, position: Int) {

    }

}