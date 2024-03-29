package com.example.userinformation.passDataFragmentToFragmentOnSingleActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.userinformation.R
import com.google.android.material.button.MaterialButton

class FirstFragment : Fragment() {

    private lateinit var messageEdit :EditText
    private lateinit var button :MaterialButton
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        messageEdit= view.findViewById(R.id.id_fragment_first_text)
        button = view.findViewById(R.id.btn_fragment_one)


        return view
    }

}