package com.example.userinformation.electronics.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentAddUserBinding
import com.example.userinformation.electronics.repository.UserRepository

class AddUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add_user, container, false)


        return view
    }

}