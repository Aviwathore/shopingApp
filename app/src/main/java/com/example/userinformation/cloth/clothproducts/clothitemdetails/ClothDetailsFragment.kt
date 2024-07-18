package com.example.userinformation.cloth.clothproducts.clothitemdetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.addtocart.AddToCartFragment
import com.example.userinformation.cloth.clothproducts.adapter.ClothAdapter
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentClothDetailsBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem
import com.example.userinformation.vibretephone.vibratePhone
import com.example.userinformation.wishlist.WishListFragment

class ClothDetailsFragment : Fragment(), OnClickListener {
    private var _binding: FragmentClothDetailsBinding? = null
    private val binding get() = _binding!!
    private var selectedProductSize = ""
    private lateinit var dbHelper: ProductDBHelper
    private var itemId = 0
    private var itemFav = 0
    private var itemCost = 0.0
    private var itemAddToCart = 0
    private var clothItem: ClothItem? = null
    private var isAddToCart = false
    private var stockCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentClothDetailsBinding.inflate(inflater, container, false)
        binding.layoutHeader.buttonEnd.setImageResource(R.drawable.heart_white)
        binding.layoutHeader.buttonEnd.visibility = View.VISIBLE

        binding.layoutHeader.buttonStart.setOnClickListener(this)
        binding.layoutHeader.buttonEnd.setOnClickListener(this)

        binding.rlAddToCart.setOnClickListener(this)

        binding.sSize.setOnClickListener(this)
        binding.mSize.setOnClickListener(this)
        binding.lSize.setOnClickListener(this)
        binding.xlSize.setOnClickListener(this)
        binding.xxlSize.setOnClickListener(this)
        binding.xxxlSize.setOnClickListener(this)
        binding.rlWishlist.setOnClickListener(this)

        dbHelper = ProductDBHelper(requireContext())

        itemId = arguments?.getInt("clothId") ?: -1

        clothItem = dbHelper.getObjectById(itemId)
        if (clothItem != null) {
            binding.txtClothItemCategory.text = clothItem!!.category
            binding.txtClothItemRate.text = clothItem!!.rating.rate.toString()
            binding.txtClothItemDes.text = clothItem!!.description
            binding.txtClothItemTitle.text = clothItem!!.title

            val price = clothItem!!.price
            itemCost = price * ClothAdapter.CONVERSION_FACTOR
            val formattedNumber = formatToIndianNumberingSystem(itemCost)
            binding.txtClothTotalPrice.text = formattedNumber

            Glide.with(requireContext())
                .load(clothItem!!.image)
                .into(binding.imgClothItem)
            itemFav = clothItem!!.is_fav
            itemAddToCart = clothItem!!.addToCart

            selectedProductSize = clothItem!!.productSize

            stockCount = clothItem!!.rating.count

        }
        else{
            Log.d("TAG", "onCreateView: ------------- cloth item is empty")
        }
        if (itemFav == 1) {
            binding.imgFav.setImageResource(R.drawable.heart_pink)
            binding.txtWishlist.text = getString(R.string.wishlisted)
        } else {
            binding.imgFav.setImageResource(R.drawable.heart_white)
            binding.txtWishlist.text = getString(R.string.wishlist)

        }

        if (itemAddToCart == 1) {
            binding.txtAddToCart.text = getString(R.string.go_to_cart)
            isAddToCart = true
        } else {
            binding.txtAddToCart.text = getString(R.string.add_to_cart)
            selectedProductSize = ""
        }

        checkSelectedSize(selectedProductSize)

        return binding.root
    }


    private fun checkSelectedSize(size: String): Boolean {
        selectedProductSize = size
        when (size) {
            getString(R.string.s) -> {
                binding.sSize.setBackgroundResource(R.drawable.selected_size_layout)
                binding.sSize.setTextColor(Color.WHITE)

                binding.mSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.mSize.setTextColor(Color.BLACK)
                binding.lSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.lSize.setTextColor(Color.BLACK)
                binding.xlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xlSize.setTextColor(Color.BLACK)
                binding.xxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxlSize.setTextColor(Color.BLACK)
                binding.xxxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxxlSize.setTextColor(Color.BLACK)
                return true
            }

            getString(R.string.m) -> {
                binding.mSize.setBackgroundResource(R.drawable.selected_size_layout)
                binding.mSize.setTextColor(Color.WHITE)

                binding.sSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.sSize.setTextColor(Color.BLACK)
                binding.lSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.lSize.setTextColor(Color.BLACK)
                binding.xlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xlSize.setTextColor(Color.BLACK)
                binding.xxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxlSize.setTextColor(Color.BLACK)
                binding.xxxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxxlSize.setTextColor(Color.BLACK)
                return true
            }

            getString(R.string.l) -> {
                binding.lSize.setBackgroundResource(R.drawable.selected_size_layout)
                binding.lSize.setTextColor(Color.WHITE)

                binding.mSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.mSize.setTextColor(Color.BLACK)
                binding.sSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.sSize.setTextColor(Color.BLACK)
                binding.xlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xlSize.setTextColor(Color.BLACK)
                binding.xxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxlSize.setTextColor(Color.BLACK)
                binding.xxxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxxlSize.setTextColor(Color.BLACK)
                return true
            }

            getString(R.string.xl) -> {
                binding.xlSize.setBackgroundResource(R.drawable.selected_size_layout)
                binding.xlSize.setTextColor(Color.WHITE)

                binding.mSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.mSize.setTextColor(Color.BLACK)
                binding.lSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.lSize.setTextColor(Color.BLACK)
                binding.sSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.sSize.setTextColor(Color.BLACK)
                binding.xxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxlSize.setTextColor(Color.BLACK)
                binding.xxxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxxlSize.setTextColor(Color.BLACK)
                return true
            }

            getString(R.string.xxl) -> {
                binding.xxlSize.setBackgroundResource(R.drawable.selected_size_layout)
                binding.xxlSize.setTextColor(Color.WHITE)

                binding.mSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.mSize.setTextColor(Color.BLACK)
                binding.lSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.lSize.setTextColor(Color.BLACK)
                binding.xlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xlSize.setTextColor(Color.BLACK)
                binding.sSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.sSize.setTextColor(Color.BLACK)
                binding.xxxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxxlSize.setTextColor(Color.BLACK)
                return true
            }

            getString(R.string.xxxl) -> {
                binding.xxxlSize.setBackgroundResource(R.drawable.selected_size_layout)
                binding.xxxlSize.setTextColor(Color.WHITE)

                binding.mSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.mSize.setTextColor(Color.BLACK)
                binding.lSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.lSize.setTextColor(Color.BLACK)
                binding.xlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xlSize.setTextColor(Color.BLACK)
                binding.xxlSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.xxlSize.setTextColor(Color.BLACK)
                binding.sSize.setBackgroundResource(R.drawable.size_option_layout)
                binding.sSize.setTextColor(Color.BLACK)
                return true
            }

            else -> {}
        }
        return false
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.button_start -> {
                requireActivity().supportFragmentManager.popBackStack()
            }


            R.id.button_end -> {
                val favFragment = WishListFragment()
                val bundle = Bundle()
                bundle.putInt("clothId", itemId)
                favFragment.arguments = bundle

                val activityDash= activity as DashBoardActivity
                activityDash.replaceFragment(favFragment)
            }

            R.id.s_size -> {
                checkSelectedSize(getString(R.string.s))
                hideSizeError()
            }

            R.id.m_size -> {
                checkSelectedSize(getString(R.string.m))
                hideSizeError()
            }

            R.id.l_size -> {
                checkSelectedSize(getString(R.string.l))
                hideSizeError()
            }

            R.id.xl_size -> {
                checkSelectedSize(getString(R.string.xl))
                hideSizeError()
            }

            R.id.xxl_size -> {
                checkSelectedSize(getString(R.string.xxl))
                hideSizeError()
            }

            R.id.xxxl_size -> {
                checkSelectedSize(getString(R.string.xxxl))
                hideSizeError()
            }

            R.id.rl_wishlist ->{
                wishListedItem()
            }

            R.id.rl_add_to_cart -> {
                if (!isAddToCart) {
                    addToCart()
                } else {
                    dbHelper.updateStockCount(itemId, stockCount - 1)

                    val detailsFragment = activity as DashBoardActivity
                    detailsFragment.replaceFragment(AddToCartFragment())
                    isAddToCart = false

                }

            }
            else -> {}
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    private fun wishListedItem() {

        dbHelper = ProductDBHelper(requireContext())

        itemFav = if (itemFav == 1) 0 else 1
        dbHelper.updateFavState(itemId, itemFav)

        if (itemFav == 1) {
            binding.txtWishlist.text = getString(R.string.wishlisted)
            binding.imgFav.setImageResource(R.drawable.herts_pink)
        } else {
            binding.txtWishlist.text = getString(R.string.wishlist)
            binding.imgFav.setImageResource(R.drawable.heart_white)
        }

    }

    private fun addToCart() {

        val isSelectedSize = checkSelectedSize(selectedProductSize)


        if (isSelectedSize) {
            hideSizeError()

            dbHelper.updateProductSize(itemId, selectedProductSize)
            if (!isAddToCart) {
                dbHelper.updateAddToCartCount(itemId, 1)
            }
            binding.txtAddToCart.text = getString(R.string.go_to_cart)
            dbHelper.updateAddToCartStatus(itemId, 1)
            isAddToCart = true

        } else {
            context?.let { vibratePhone(it, 300) }
            binding.txtSizeError.visibility = View.VISIBLE
        }

    }

    private fun hideSizeError() {
        binding.txtSizeError.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}

