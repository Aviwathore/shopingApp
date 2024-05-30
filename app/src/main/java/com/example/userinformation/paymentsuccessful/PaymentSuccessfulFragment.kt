package com.example.userinformation.paymentsuccessful

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentPaymentSuccessfulBinding

class PaymentSuccessfulFragment : Fragment() {

    private lateinit var binding:FragmentPaymentSuccessfulBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPaymentSuccessfulBinding.inflate(layoutInflater, container, false)

        binding.headerLayout.buttonStart.visibility = View.GONE
        binding.headerLayout.txtHeader.text = getString(R.string.payment_done)
        return  binding.root
    }

}