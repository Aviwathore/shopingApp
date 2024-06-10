package com.example.userinformation.paymentsuccessful

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentPaymentSuccessfulBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.formatNumber.formatToIndianNumberingSystem

class PaymentSuccessfulFragment : Fragment() {

    private lateinit var binding:FragmentPaymentSuccessfulBinding
    private lateinit var dbHelper: ProductDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPaymentSuccessfulBinding.inflate(layoutInflater, container, false)

        binding.headerLayout.buttonStart.visibility = View.GONE
        binding.headerLayout.txtHeader.text = getString(R.string.payment_details)

        dbHelper = ProductDBHelper(requireContext())

        val user = dbHelper.getObjectById(11)

        if (user?.paymentType == "Credit card\t\t") {

            val totalAmount = user.totalCost.toDouble()
            binding.txtTotalAmount.text = formatToIndianNumberingSystem(totalAmount)

            binding.txtCardHolderName.text = user.cardHolderName
            binding.txtCardNumber.text = user.cartNumber.toString()
        } else {
            binding.imgCard.setImageResource(R.drawable.cash)
            val totalAmount = user?.totalCost?.toDouble()
            binding.txtTotalAmount.text = totalAmount?.let { formatToIndianNumberingSystem(it) }

            binding.txtCardHolderName.text = getString(R.string.rohini)
            binding.txtCardNumber.text = getString(R.string.cash)
        }


        binding.btnPaymentDone.setOnClickListener {
            startActivity(Intent(context, DashBoardActivity::class.java))
        }

        return  binding.root
    }

}