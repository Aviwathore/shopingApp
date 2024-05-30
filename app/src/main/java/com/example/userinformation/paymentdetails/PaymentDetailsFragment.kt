package com.example.userinformation.paymentdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentPaymentDetailsBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem
import com.example.userinformation.paymentdetails.adapter.CartProductAdapter

class PaymentDetailsFragment : Fragment(), OnClickListener, CartProductAdapter.OnProductChangedListener {
    private lateinit var binding: FragmentPaymentDetailsBinding
    private lateinit var dbHelper: ProductDBHelper
    private var cartProductList: MutableList<ClothItem> = mutableListOf()
    private var filterProduct: List<ClothItem> = listOf()
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentDetailsBinding.inflate(layoutInflater, container, false)

        binding.headerLayout.txtHeader.text = getString(R.string.payment_details)
        binding.headerLayout.buttonStart.setOnClickListener(this)

        dbHelper = ProductDBHelper(requireContext())

        cartProductList = dbHelper.getAllClothItems().toMutableList()

        filterProduct = cartProductList.filter { it.addToCart == 1 }
        fetchCartProductDetails()
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.productRecyclerview

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = CartProductAdapter()

        adapter.setCartProduct(filterProduct)

        recyclerView.adapter = adapter

    }

    @SuppressLint("SetTextI18n")
    private fun fetchCartProductDetails() {

        val product = filterProduct[0]
        val totalMRP: Double = product.totalCost.toDouble()

        val formattedTotalMRP = formatToIndianNumberingSystem(totalMRP)

        binding.txtSubTotalCost.text = formattedTotalMRP
        binding.txtTotalCost.text = formattedTotalMRP

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start -> activity?.onBackPressed()
//            R.id.rl_proceed_checkout
        }
    }

    override fun onProductChangedListener(newTotalPrice: Double, position: Int) {
        fetchCartProductDetails()
    }


}