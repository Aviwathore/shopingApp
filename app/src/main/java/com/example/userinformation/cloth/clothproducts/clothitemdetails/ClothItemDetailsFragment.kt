package com.example.userinformation.cloth.clothproducts.clothitemdetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentClothItemDetailsBinding

class ClothItemDetailsFragment : Fragment(), OnClickListener {
    private var _binding: FragmentClothItemDetailsBinding? = null
    private val binding get() = _binding!!
    private var selected = ""
    private var isWishListed = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentClothItemDetailsBinding.inflate(inflater, container, false)
        binding.layoutHeader.buttonStart.setOnClickListener(this)
        binding.sSize.setOnClickListener(this)
        binding.mSize.setOnClickListener(this)
        binding.lSize.setOnClickListener(this)
        binding.xlSize.setOnClickListener(this)
        binding.xxlSize.setOnClickListener(this)
        binding.xxxlSize.setOnClickListener(this)
        binding.rlWishlist.setOnClickListener(this)

        val itemTitle = arguments?.getString("clothTitle")
        val itemCategory = arguments?.getString("clothCategory")
        val itemImage = arguments?.getString("clothImg")
        val itemRate = arguments?.getString("clothRate")
        val itemDesc = arguments?.getString("clothDesc")
        val itemPrice = arguments?.getString("clothPrice")

        binding.txtClothItemTitle.text = itemTitle
        binding.txtClothItemRate.text = itemRate
        binding.txtClothItemCategory.text = itemCategory
        binding.txtClothItemDes.text = itemDesc
        binding.txtClothTotalPrice.text = itemPrice

        Glide.with(requireContext())
            .load(itemImage)
            .into(binding.imgClothItem)

//        listedItemsStyle()
        return binding.root
    }

//    private fun listedItemsStyle() {
//        dotStyle(binding.txtProductType1, Color.BLACK, 1.5f)
//        dotStyle(binding.txtProductType2, Color.BLACK, 1.5f)
//        dotStyle(binding.txtProductType3, Color.BLACK, 1.5f)
//        dotStyle(binding.txtProductType4, Color.BLACK, 1.5f)
//        dotStyle(binding.txtProductType5, Color.BLACK, 1.5f)
//        dotStyle(binding.txtProductType6, Color.BLACK, 1.5f)
//
//    }

    private fun checkSelectedSize(size: String) {
        selected = size
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
            }

            else -> {}
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.button_start -> requireActivity().supportFragmentManager.popBackStack()
            R.id.s_size -> checkSelectedSize(getString(R.string.s))

            R.id.m_size -> checkSelectedSize(getString(R.string.m))

            R.id.l_size -> checkSelectedSize(getString(R.string.l))
            R.id.xl_size -> checkSelectedSize(getString(R.string.xl))

            R.id.xxl_size -> checkSelectedSize(getString(R.string.xxl))

            R.id.xxxl_size -> checkSelectedSize(getString(R.string.xxxl))

            R.id.rl_wishlist -> wishListedItem()

            else -> {}
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    private fun wishListedItem() {
        isWishListed = if (isWishListed == 0) 1 else 0

        val drawableResource = if (isWishListed == 0) {
            binding.txtWishlist.text = getString(R.string.wishlist)
            R.drawable.hearts

        } else {
            binding.txtWishlist.text = getString(R.string.wishlisted)
            R.drawable.heart_1
        }
        binding.imgFav.setImageResource(drawableResource)

    }


}

