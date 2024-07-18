package com.example.userinformation.addtocart

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.addtocart.adapter.AddToCartAdapter
import com.example.userinformation.checkOutPaymentdetails.CheckOutPaymentDetailsFragment
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentAddToCartBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem
import com.example.userinformation.wishlist.WishListFragment

class AddToCartFragment : Fragment(),
    AddToCartAdapter.OnProductCountChangedListener,
    View.OnClickListener {
    private lateinit var binding: FragmentAddToCartBinding
    private lateinit var dbHelper: ProductDBHelper
    private lateinit var adapter: AddToCartAdapter
    private lateinit var recyclerView: RecyclerView
    private var cartProductList: MutableList<ClothItem> = mutableListOf()
    private var itemInCart = 0
    private var totalMRP = ""

    private var filterProduct: List<ClothItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddToCartBinding.inflate(inflater, container, false)
        binding.layoutHeader.txtHeader.text = getString(R.string.my_Cart)
        binding.layoutHeader.buttonEnd.setImageResource(R.drawable.heart_white)

        binding.layoutHeader.buttonStart.setOnClickListener(this)
        binding.layoutHeader.buttonEnd.setOnClickListener(this)


        val gifImg = binding.imgGifAnimation
        Glide.with(this)
            .asGif()
            .load(R.drawable.animation)
            .into(gifImg)

        adapter = context?.let { AddToCartAdapter(it, this) }!!
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        fetchCartProductDetails()
        binding.txtProductCount.text = itemInCart.toString()
        binding.txtAllProductCost.text = totalMRP.toString()

        binding.txtTotalCount.text = totalMRP

        binding.rlProceedCheckout.setOnClickListener {
            val formattedTotalMRP = totalMRP.replace("₹", "").replace(",", "")
            val totalCost = formattedTotalMRP.toLong()
            dbHelper.updateTotalMRP(totalCost)
            val dashBoardActivity= activity as DashBoardActivity

            dashBoardActivity.replaceFragment(CheckOutPaymentDetailsFragment())
        }
        return binding.root

    }



    @SuppressLint("NotifyDataSetChanged")
    private fun fetchCartProductDetails() {

        dbHelper = ProductDBHelper(requireContext())

        cartProductList = dbHelper.getAllClothItems().toMutableList()

        filterProduct = cartProductList.filter { it.addToCart == 1 }
        itemInCart = filterProduct.size

        if (filterProduct.isEmpty()) {
            binding.emptyProductLayout.root.visibility = View.VISIBLE
            binding.svCart.visibility = View.GONE
            binding.rlProceedCheckout.visibility = View.GONE




        } else {
            val productPrice = filterProduct.sumOf { it.price * it.productCount }
            val totalCost = productPrice * 83.50
            totalMRP = formatToIndianNumberingSystem(totalCost)

            binding.txtAllProductCost.text = totalMRP
            binding.txtTotalCount.text = totalMRP

            adapter.setCartProduct(filterProduct)

            adapter.notifyDataSetChanged()
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.button_start -> {
               activity?.onBackPressed()
//                requireActivity().supportFragmentManager.popBackStack()
//                bottomNva.selectedItemId = R.id.navigation_list
//                val wishlistNavigation = v.findViewById(R.id)
            }

            R.id.button_end -> {

                val dashBoardActivity= activity as DashBoardActivity
                dashBoardActivity.replaceFragment(WishListFragment())

            }

            else -> {}
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onProductCountChanged(newTotalPrice: Double, position: Int) {

        fetchCartProductDetails()
    }

    override fun onDestroyView() {
        Log.d("TAG", "onDestroy: ------------addtocart")
        super.onDestroyView()
    }



}