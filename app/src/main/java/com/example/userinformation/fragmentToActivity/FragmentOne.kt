package com.example.userinformation.fragmentToActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentOneBinding

class FragmentOne : Fragment() {

    private lateinit var text1:TextView
    private lateinit var text2: TextView
//    private lateinit var binding: FragmentOneBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        binding = FragmentOneBinding.inflate(layoutInflater)
//        binding.root
//
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_one, container, false)

//        text1 = binding.fragmentTextOne
//        text2 = binding.fragmentTextTwo

        text1 = view.findViewById(R.id.fragmentTextOne)
        text2 = view.findViewById(R.id.fragmentTextTwo)

        // get the data from pass the bundle

        val bundle = arguments
        val name = bundle?.getString("cName")
        val contact = bundle?.getString("cContact")

        text1.text=name
        text2.text=contact

        return view

    }

}