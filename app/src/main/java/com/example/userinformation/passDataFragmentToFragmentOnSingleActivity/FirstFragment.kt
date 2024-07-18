package com.example.userinformation.passDataFragmentToFragmentOnSingleActivity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.userinformation.R
import com.example.userinformation.passDataFragmentToFragmentOnSingleActivity.fragmentInterface.PassDataFragmentToFragmentActivity

class FirstFragment : Fragment() {

    private lateinit var messageEdit :EditText
    private lateinit var button :AppCompatButton
    private lateinit var passDataListener: PassDataFragmentToFragmentActivity
//    private lateinit var  binding: FragmentFirstBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        binding = FragmentFirstBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        messageEdit= view.findViewById(R.id.id_fragment_first_text)
        button = view.findViewById(R.id.btn_fragment_one)

    button.setOnClickListener {
        val data = messageEdit.text.toString().trim()
        Log.d("data", "yesss")
        passDataListener.passData(data)
        Log.d("passdata","yesss")
    }
//        return binding.root
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PassDataFragmentToFragmentActivity) {
            passDataListener = context
            Log.d("attach", "yess")  // done
        } else {
            throw ClassCastException("$context must implement PassDataFragmentToFragment")
        }
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.btnFragmentOne
//    }
}