package com.example.userinformation.myorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.databinding.FragmentActiveBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.model.ClothItem

class ActiveFragment : Fragment() {

    private lateinit var binding: FragmentActiveBinding
    private lateinit var dbHelper: ProductDBHelper
    private var filterProduct: List<ClothItem> = listOf()
    private var productOrderStatus = "yes"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiveBinding.inflate(inflater, container, false)

        dbHelper = ProductDBHelper(requireContext())

        filterProduct = dbHelper.getOrderActiveProduct(productOrderStatus)


        setupRecyclerView()
        return binding.root

    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerview

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ActiveProductAdapter(requireContext())

        adapter.setCartProduct(filterProduct)

        recyclerView.adapter = adapter


    }

}