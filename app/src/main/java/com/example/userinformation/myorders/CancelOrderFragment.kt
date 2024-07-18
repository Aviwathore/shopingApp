package com.example.userinformation.myorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userinformation.databinding.FragmentCancleOrderBinding

class CancelOrderFragment : Fragment() {

    private lateinit var binding: FragmentCancleOrderBinding
//    private lateinit var dbHelper: ProductDBHelper
//    private var filterProduct: List<ClothItem> = listOf()
//    private var productOrderStatus = "yes"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCancleOrderBinding.inflate(inflater, container, false)

//        dbHelper = ProductDBHelper(requireContext())
//
//        filterProduct = dbHelper.getOrderActiveProduct(productOrderStatus)
//
//
//        setupRecyclerView()
        return binding.root
    }


//    private fun setupRecyclerView() {
//        val recyclerView = binding.recyclerview
//
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        val adapter = ActiveProductAdapter(requireContext())
//
//        adapter.setCartProduct(filterProduct)
//
//        recyclerView.adapter = adapter
//
//
//    }
}