package com.example.userinformation.activityLifeCycle.customdialogbox

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.userinformation.R

class MyFragmentClass :DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val rootView:View = inflater.inflate(R.layout.custom_dialog_box_layout, container,false)

        val rootView: View = inflater.inflate(R.layout.custom_dialog_box_layout, container, false)
        val cancelButton = rootView.findViewById<AppCompatButton>(R.id.btn_cancel_dialog)
        val submitButton = rootView.findViewById<AppCompatButton>(R.id.btn_submit_dialog)
        val surveyRadioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        cancelButton.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                dismiss()
            }
        })

        submitButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(v: View?) {
                val selectedId = surveyRadioGroup.checkedRadioButtonId
                val selectedRadioButton = rootView.findViewById<RadioButton>(selectedId)

                /* Toast.makeText(context, selectedRadioButton.text.toString(), Toast.LENGTH_SHORT).show() */
                Log.d("CUSTOM DIALOG BOX", selectedRadioButton.text.toString())
                dismiss()
            }

        })


        return rootView
    }
}