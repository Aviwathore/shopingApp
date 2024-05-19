package com.example.userinformation.cloth.clothproducts.favouriteCloth

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
import com.example.userinformation.cloth.clothproducts.ClothFragment
import com.example.userinformation.cloth.clothproducts.adapter.ClothAdapter
import com.example.userinformation.cloth.clothproducts.clothitemdetails.ClothItemDetailsFragment
import com.example.userinformation.cloth.clothproducts.model.ClothItem
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentFavouriteClothBinding
import com.example.userinformation.informationform.dbHelper.InformationFormDBHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class FavouriteClothFragment : Fragment(), ClothAdapter.OnItemClickListener, OnClickListener {

    private lateinit var binding: FragmentFavouriteClothBinding
    private lateinit var adapter: ClothAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: InformationFormDBHelper
    private lateinit var bottomNva: BottomNavigationView
    private var favouriteClothList: List<ClothItem> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteClothBinding.inflate(inflater, container, false)

        binding.layoutHeader.txtHeader.text = getString(R.string.MyProduct)
        binding.layoutHeader.buttonStart.setOnClickListener(this)

        bottomNva = binding.bottomNavigationItem.bottomNavigationView
        bottomNva.selectedItemId = R.id.navigation_fav

        adapter = ClothAdapter(this)
        recyclerView = binding.recyclerViewList
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        fetchFavouriteCloth()
        bottomNavigationItemSelected()
        return binding.root
    }

    private fun fetchFavouriteCloth() {
        dbHelper = InformationFormDBHelper(requireContext())

        favouriteClothList = dbHelper.getAllClothItems()

        val filterFavCloth = favouriteClothList.filter { it.is_fav == 1 }
        adapter.setClothItem(filterFavCloth)

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
        adapter.setClothItem(filterFavCloth)
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

        bottomNva.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(context, DashBoardActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_cart -> {
                    Toast.makeText(context, "cart is clicked!!", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.navigation_profile -> {
                    val fragment = ClothFragment()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.cloth_fragment, fragment).commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start -> activity?.onBackPressed()
            else ->{}
        }
    }
}