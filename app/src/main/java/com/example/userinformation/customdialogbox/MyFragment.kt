package com.example.userinformation.customdialogbox

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.userinformation.R

class MyFragment private constructor():DialogFragment() {

    private  var selectedOption: Int=-1
    companion object{

        @Volatile
        private var instance:MyFragment?=null

        // Singleton getInstance Method

        fun getInstance(): MyFragment{
            return instance?: MyFragment().also { instance = it
            }
        }

    }

    fun showDialog(fragmentManager: FragmentManager, tag: String) {

        if (fragmentManager.findFragmentByTag(tag)==null){
            show(fragmentManager,tag)
        }
    }

    fun dismissDialog(){
        selectedOption=-1
        dismiss()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setSelectedOption(option: Int) {
        selectedOption = option
    }
    fun getSelectedOption(): Int {
        return selectedOption
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView:View = inflater.inflate(R.layout.custom_dialog_box_layout, container, false)

        val cancelButton = rootView.findViewById<AppCompatButton>(R.id.btn_cancel_dialog)
        val submitButton = rootView.findViewById<AppCompatButton>(R.id.btn_submit_dialog)
        val sButton = rootView.findViewById<RadioGroup>(R.id.radio_group)

        cancelButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(context, "Terminate", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        })

        Log.d("cancel", "custom dialog")

        submitButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
//                TODO("Not yet implemented")
                val selectId = sButton.checkedRadioButtonId
                val selectedRadioButtonId = rootView.findViewById<RadioButton>(selectId)

                Log.d("review", selectedRadioButtonId.text.toString())
                Toast.makeText(context, selectedRadioButtonId.text.toString(), Toast.LENGTH_SHORT).show()
                dismiss()
            }

        })

        return rootView
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        selectedOption = -1
    }
}