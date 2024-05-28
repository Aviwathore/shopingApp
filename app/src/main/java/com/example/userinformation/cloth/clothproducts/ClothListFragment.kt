package com.example.userinformation.cloth.clothproducts

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.userinformation.R
import com.example.userinformation.addtocart.AddToCartFragment
import com.example.userinformation.cloth.clothproducts.adapter.ClothAdapter
import com.example.userinformation.cloth.clothproducts.clothitemdetails.ClothDetailsFragment
import com.example.userinformation.cloth.clothproducts.model.ClothItem
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentClothlistBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.wishlist.WishListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClothListFragment : Fragment(), OnClickListener, ClothAdapter.OnItemClickListener {

    private var _binding: FragmentClothlistBinding? = null
    private lateinit var dbHelper: ProductDBHelper
    private val binding get() = _binding!!
    private lateinit var adapter: ClothAdapter
    private lateinit var recyclerView: RecyclerView
    private var clothItemList: List<ClothItem> = listOf()
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentClothlistBinding.inflate(inflater, container, false)

        binding.layoutHeader.buttonEnd.visibility = View.GONE
        binding.layoutHeader.buttonStart.setOnClickListener(this)


        adapter = context?.let { ClothAdapter(this, it) }!!

        swipeRefreshLayout = binding.swipeFreshLayout
        progressBar = binding.progressBar

        recyclerView = binding.rvCloth
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            fetchClothItems()
        }

        fetchClothItems()

        bottomNavigationView = binding.bottomNavigationItemView.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.navigation_list


        bottomNavigationItemSelected()

        return binding.root
    }


    private fun bottomNavigationItemSelected() {
        bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_fav -> {

                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(WishListFragment())

                    true
                }

                R.id.navigation_home -> {
                    startActivity(Intent(context, DashBoardActivity::class.java))
                    true
                }

                R.id.navigation_cart -> {

                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(AddToCartFragment())
                    true
                }

                else -> false
            }
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start -> {
                startActivity(Intent(context, DashBoardActivity::class.java))
            }
            else -> {}
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchClothItems() {
        progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
        dbHelper = ProductDBHelper(requireContext())

        clothItemList = dbHelper.getAllClothItems().toMutableList()


        val filterItem =
            clothItemList.filter { it.category == "men's clothing" || it.category == "women's clothing" }

        adapter.setClothItem(filterItem)
        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
        }, 200)

    }

    @SuppressLint("NotifyDataSetChanged", "DetachAndAttachSameFragment")
    override fun addToFavClickListener(item: ClothItem, position: Int) {
        for (cloth in clothItemList) {
            if (cloth.id == item.id) {

                cloth.is_fav = if (cloth.is_fav == 1) 0 else 1
                updateFavStateItem(cloth)
            }
        }
        clothItemList = dbHelper.getAllClothItems()
        val filterItem =
            clothItemList.filter { it.category == "men's clothing" || it.category == "women's clothing" }

        adapter.setClothItem(filterItem)
        adapter.notifyDataSetChanged()

    }

    override fun onClothItemClickListener(item: ClothItem, position: Int) {

        val bundle = Bundle()
        bundle.putInt("clothId", item.id)

        val clothInstance = ClothDetailsFragment()
        clothInstance.arguments = bundle

        val dashBoardActivity = activity as DashBoardActivity
        dashBoardActivity.replaceFragment(clothInstance)

    }


    private fun updateFavStateItem(item: ClothItem) {
        dbHelper.updateFavState(item.id, item.is_fav)
    }

    override fun onResume() {
        super.onResume()

    }


}