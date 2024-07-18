package com.example.userinformation.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentFirst2Binding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirst2Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirst2Binding.inflate(inflater, container, false)
        val view = binding.root

        binding.txtFirstFragment.setOnClickListener{
            val navController = binding.txtFirstFragment.findNavController()

            navController.navigate(R.id.secondFragment)
        }

        return view
    }

}
