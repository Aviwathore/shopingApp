package com.example.userinformation.electronics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.userinformation.R
import com.example.userinformation.databinding.FragmentListOfUserkBinding

class ListOfUserkFragment : Fragment() {
    private var _binding: FragmentListOfUserkBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListOfUserkBinding.inflate(inflater,container,false)
//        val view= inflater.inflate(R.layout.fragment_list_of_userk, container, false)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listOfUserkFragment_to_addUserFragment22)
        }
        return binding.root
    }

}