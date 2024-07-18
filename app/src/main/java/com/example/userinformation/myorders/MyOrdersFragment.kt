package com.example.userinformation.myorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentMyOrdersBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.model.ClothItem
import com.example.userinformation.paymentsuccessful.PaymentSuccessfulFragment
import com.google.android.material.tabs.TabLayoutMediator

class MyOrdersFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentMyOrdersBinding
    private lateinit var dbHelper: ProductDBHelper
    private var orderConfirmProductList: List<ClothItem> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyOrdersBinding.inflate(inflater, container, false)
        binding.headerLayout.txtHeader.text = getString(R.string.my_orders)
        binding.headerLayout.buttonEnd.setImageResource(R.drawable.baseline_article_24)

        dbHelper = ProductDBHelper(requireContext())
        orderConfirmProductList = dbHelper.getOrderActiveProduct("yes")

        binding.headerLayout.buttonStart.setOnClickListener(this)
        binding.headerLayout.buttonEnd.setOnClickListener(this)

        val adapter = ViewPagerAdapter(this)


        adapter.addFragment(ActiveFragment(), "Active")
        adapter.addFragment(CompletedFragment(), "Completed")
        adapter.addFragment(CancelOrderFragment(), "Cancelled")


        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = adapter.mFragmentTitleList[position]
        }.attach()



        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start -> {
                activity?.onBackPressed()
            }

            R.id.button_end -> {

                if (orderConfirmProductList.isNotEmpty()) {

                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(PaymentSuccessfulFragment())
                } else {
                    binding.headerLayout.buttonEnd.isEnabled = false
                }
            }
        }
    }
}