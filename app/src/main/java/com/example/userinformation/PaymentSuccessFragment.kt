package com.example.userinformation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentPaymentSuccessBinding
import com.example.userinformation.paymentsuccessful.PaymentSuccessfulFragment

class PaymentSuccessFragment : Fragment(), OnClickListener {

    private var _binding: FragmentPaymentSuccessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("TAG", "onCreateView: ---------------------backpress")

        _binding = FragmentPaymentSuccessBinding.inflate(layoutInflater, container, false)

        binding.headerLayout.txtHeader.text = getString(R.string.payment)
        binding.headerLayout.buttonStart.isVisible = false


        binding.headerLayout.buttonEnd.setImageResource(R.drawable.close)
        binding.headerLayout.buttonEnd.setOnClickListener(this)
        binding.btnViewEReceipt.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                Log.d("TAG", "Back button pressed, but action is disabled.")

            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_end -> startActivity(Intent(context, DashBoardActivity::class.java))
            R.id.btn_view_e_receipt -> {

                val dashBoardActivity = activity as DashBoardActivity
                dashBoardActivity.replaceFragment(PaymentSuccessfulFragment())
            }

        }
    }


}