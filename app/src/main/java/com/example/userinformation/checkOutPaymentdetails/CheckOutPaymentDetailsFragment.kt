package com.example.userinformation.checkOutPaymentdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.cardoptionforpayment.CreditCardFragment
import com.example.userinformation.checkOutPaymentdetails.adapter.CartProductAdapter
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentCheckOutPaymentDetailsBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem
import com.example.userinformation.model.ClothItem
import com.example.userinformation.paymentsuccess.PaymentSuccessFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class CheckOutPaymentDetailsFragment : Fragment(), OnClickListener, CartProductAdapter.OnProductChangedListener {
    private lateinit var binding: FragmentCheckOutPaymentDetailsBinding
    private lateinit var dbHelper: ProductDBHelper
    private var cartProductList: MutableList<ClothItem> = mutableListOf()
    private var filterProduct: List<ClothItem> = listOf()


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckOutPaymentDetailsBinding.inflate(layoutInflater, container, false)

        binding.headerLayout.txtHeader.text = getString(R.string.checkout)
        binding.headerLayout.buttonStart.setOnClickListener(this)
        binding.rlProceedCheckout.setOnClickListener(this)

        dbHelper = ProductDBHelper(requireContext())

        cartProductList = dbHelper.getAllClothItems().toMutableList()

        filterProduct = cartProductList.filter { it.addToCart == 1 }

        if (filterProduct.isNotEmpty()){

            fetchCartProductDetails()
        }
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
            R.id.rl_proceed_checkout -> {
                paymentOptionBottomSheetDialog()

            }
        }
    }

    private fun paymentOptionBottomSheetDialog() {
        val bottomSheetDialog = context?.let { BottomSheetDialog(it) }
        val view = layoutInflater.inflate(R.layout.payment_option_layout, null)


        bottomSheetDialog?.setContentView(view)
        bottomSheetDialog?.show()

        val button = view.findViewById<RadioButton>(R.id.rd_credit_cart)
        val continueButton = view.findViewById<RelativeLayout>(R.id.rl_proceed_checkout)
        val cancelButton = view.findViewById<ImageView>(R.id.img_cancel)

        var isRadioButtonClick = false
        button.setOnCheckedChangeListener { _, isChecked ->
            isRadioButtonClick = isChecked
            if (isRadioButtonClick) {
                continueButton.isEnabled
                continueButton.setBackgroundResource(R.drawable.btn_crimson)

                continueButton.setOnClickListener {
                    dbHelper.updatePaymentType(button.text.toString())
                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(CreditCardFragment())
                    bottomSheetDialog?.dismiss()
                }
            }
        }
        cancelButton.setOnClickListener {
            bottomSheetDialog?.dismiss()
        }

        val cashButton = view.findViewById<RadioButton>(R.id.rd_cash)

        cashButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                continueButton.isEnabled
                continueButton.setBackgroundResource(R.drawable.btn_crimson)

                continueButton.setOnClickListener {
                    dbHelper.updatePaymentType(cashButton.text.toString())
                    dbHelper.updateOrderConfirmStatus(1,0,"yes",1,0,0)
                    val dashBoardActivity = activity as DashBoardActivity
                    dashBoardActivity.replaceFragment(PaymentSuccessFragment())
                    bottomSheetDialog?.dismiss()
                }
            }
        }
    }

    override fun onProductChangedListener(newTotalPrice: Double, position: Int) {
        fetchCartProductDetails()
    }


}