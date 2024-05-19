package com.example.userinformation.cloth.clothproducts

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.cloth.clothproducts.adapter.ClothAdapter
import com.example.userinformation.cloth.clothproducts.clothitemdetails.ClothItemDetailsFragment
import com.example.userinformation.cloth.clothproducts.favouriteCloth.FavouriteClothFragment
import com.example.userinformation.cloth.clothproducts.model.ClothItem
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentClothBinding
import com.example.userinformation.informationform.dbHelper.InformationFormDBHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class ClothFragment : Fragment(), OnClickListener, ClothAdapter.OnItemClickListener {

    private var _binding: FragmentClothBinding? = null
    private lateinit var dbHelper: InformationFormDBHelper
    private val binding get() = _binding!!
    private lateinit var adapter: ClothAdapter
    private lateinit var recyclerView: RecyclerView
    private var clothItemList: List<ClothItem> = listOf()
    private lateinit var bottomNav :BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentClothBinding.inflate(inflater, container, false)
//        binding.bottomNavigationSelectedItem.bottomNavigation.selectedItemId = R.id.navigation_fav
//        binding.bottomNavigationSelectedItem.bottomNavigationView.selectedItemId = R.id.navigation_fav


        binding.layoutHeader.buttonEnd.visibility = View.GONE
        binding.layoutHeader.buttonStart.setOnClickListener(this)
        adapter = ClothAdapter(this)
        recyclerView = binding.rvCloth
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        fetchClothItems()

        bottomNav = binding.bottomNavigationItem.bottomNavigationView
        bottomNav.selectedItemId= R.id.navigation_profile

        bottomNavigationItemSelected()

        return binding.root
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start -> activity?.onBackPressed()

            else -> {}
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchClothItems() {
        dbHelper = InformationFormDBHelper(requireContext())
        clothItemList = dbHelper.getAllClothItems()
        val filterItem =
            clothItemList.filter { it.category == "men's clothing" || it.category == "women's clothing" }

        adapter.setClothItem(filterItem)

    }


    @SuppressLint("NotifyDataSetChanged", "DetachAndAttachSameFragment")
    override fun addToFavClickListener(item: ClothItem, position: Int) {
        for (cloth in clothItemList) {
            if (cloth.id == item.id) {

                cloth.is_fav = if (cloth.is_fav == 1) 0 else 1
                updateFavStateItem(cloth)
            }
        }

        val filterItem =
            clothItemList.filter { it.category == "men's clothing" || it.category == "women's clothing" }

        adapter.setClothItem(filterItem)
        adapter.notifyDataSetChanged()

    }

    override fun onClothItemClickListener(item: ClothItem, position: Int) {
        val bundle = Bundle()

        bundle.putString("clothTitle", item.title)
        bundle.putString("clothCategory", item.category)
        bundle.putString("clothImg", item.image)
        bundle.putString("clothRate", item.rating.rate.toString())

        val price = item.price
        val total_price = price * ClothAdapter.CONVERSION_FACTOR

        val formattedPrice =
            NumberFormat.getCurrencyInstance(Locale("en", "IN")).format(total_price)

        bundle.putString("clothPrice", formattedPrice)
        bundle.putString("clothDesc", item.description)

        val clothInstance = ClothItemDetailsFragment()

        clothInstance.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_cloth_item_detail, clothInstance, "Cloth Item Details")
            .addToBackStack(null)
            .commit()

    }


    private fun updateFavStateItem(item: ClothItem) {
        dbHelper.updateFavState(item)
    }

    private fun bottomNavigationItemSelected() {
        val favClothFragment = FavouriteClothFragment()
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {

                R.id.navigation_home -> {
                    val intent = Intent(context, DashBoardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_fav -> {
                    requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragment_cloth_item_detail, favClothFragment).commit()
                    true
                }
                R.id.navigation_cart ->{
                    Toast.makeText( context,"cart is clicked!!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

}