package com.example.userinformation.passDataFragmentToFragmentOnSingleActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.userinformation.R

class SecondFragment : Fragment() {

    private lateinit var textMessage :TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        textMessage = view.findViewById(R.id.id_fragment_second)

        return view
    }
    fun displayReceivedData(data: String) {
        textMessage.text = data
    }

}