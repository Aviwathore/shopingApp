package com.example.userinformation.wishlist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.addtocart.AddToCartFragment
import com.example.userinformation.cloth.clothproducts.ClothListFragment
import com.example.userinformation.cloth.clothproducts.adapter.ClothAdapter
import com.example.userinformation.cloth.clothproducts.clothitemdetails.ClothDetailsFragment
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentWishlistBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.model.ClothItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class WishListFragment : Fragment(), ClothAdapter.OnItemClickListener, OnClickListener {

    private lateinit var binding: FragmentWishlistBinding
    private lateinit var adapter: ClothAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var dbHelper: ProductDBHelper
    private lateinit var bottomNva: BottomNavigationView
    private var favouriteClothList: List<ClothItem> = listOf()
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)

        binding.layoutHeader.txtHeader.text = getString(R.string.WishlistCollections)
        binding.layoutHeader.buttonStart.setOnClickListener(this)

       progressBar= binding.progressBar

        binding.emptyProductLayout.txtNoProductSelectedMessage.text= getString(R.string.add_some_wishlist_product)
        binding.emptyProductLayout.imgEmptyBag.setImageResource(R.drawable.wishlist)

        adapter = context?.let { ClothAdapter(this, it) }!!
        recyclerView = binding.recyclerViewList
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        bottomNva = binding.bottomNavigationItem.bottomNavigationView
        bottomNva.selectedItemId = R.id.navigation_fav
        fetchFavouriteCloth()

        bottomNavigationItemSelected()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchFavouriteCloth() {
        progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            dbHelper = ProductDBHelper(requireContext())

        favouriteClothList = dbHelper.getAllClothItems()

        val filterFavCloth = favouriteClothList.filter { it.is_fav == 1 }

            if (filterFavCloth.isEmpty()) {
                binding.emptyProductLayout.root.visibility = View.VISIBLE
                binding.bottomNavigationItem.bottomNavigationView.visibility = View.GONE
                binding.recyclerViewList.visibility = View.GONE
            }
            else{
                adapter.setClothItem(filterFavCloth)
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
               binding.emptyProductLayout.root.visibility = View.GONE
//                 binding.bottomNavigationItem.bottomNavigationView.visibility = View.VISIBLE
                binding.recyclerViewList.visibility = View.VISIBLE
            }

        }, 200)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun addToFavClickListener(item: ClothItem, position: Int) {
        for (cloth in favouriteClothList) {
            if (cloth.id == item.id) {

                cloth.is_fav = if (cloth.is_fav == 1) 0 else 1
                updateFavStateItem(cloth)
            }
        }
        val filterFavCloth = favouriteClothList.filter { it.is_fav==1 }

        if (filterFavCloth.isEmpty()) {
            binding.emptyProductLayout.root.visibility = View.VISIBLE
            binding.bottomNavigationItem.bottomNavigationView.visibility = View.GONE
            binding.recyclerViewList.visibility = View.GONE
        }

        adapter.setClothItem(filterFavCloth)
        adapter.notifyDataSetChanged()

    }

    override fun onClothItemClickListener(item: ClothItem, position: Int) {

        val bundle = Bundle()

        bundle.putInt("clothId", item.id)
        val clothInstance = ClothDetailsFragment()

        clothInstance.arguments = bundle

        val activityDash = activity as DashBoardActivity
        activityDash.replaceFragment(clothInstance)

    }

    private fun updateFavStateItem(item: ClothItem) {
        dbHelper.updateFavState(item.id, item.is_fav)
    }
    private fun bottomNavigationItemSelected() {

        bottomNva.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(context, DashBoardActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_cart -> {

                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(AddToCartFragment())

                    true
                }

                R.id.navigation_list -> {
                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(ClothListFragment())
                    true
                }

                else -> false
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start -> {
                activity?.onBackPressed()
//                requireActivity().supportFragmentManager.popBackStack()
                bottomNva.selectedItemId = R.id.navigation_list
            }
            else ->{}
        }
    }
    override fun onDestroyView() {
        Log.d("TAG", "onDestroy: ------------wishlist")
        super.onDestroyView()
    }
    override fun onResume() {
        Log.d("TAG", "onResume:$$$$$$$$$$$$$$$$$ ----------wishlist")
//        binding.bottomNavigationItem.bottomNavigationView.selectedItemId = R.id.navigation_fav
        super.onResume()
//        bottomNavigationView.menu.getItem(POSITION).isChecked = true

//        binding.bottomNavigationItem.bottomNavigationView.menu.getItem(R.id.navigation_fav).isChecked= true

    }

}