package com.example.userinformation.customconfirmbottomsheetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import com.example.userinformation.databinding.PaymentOptionLayoutBinding

class PaymentOptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.payment_option_layout, container, false)
        val binding = PaymentOptionLayoutBinding.bind(rootView)

        return  rootView
    }


}