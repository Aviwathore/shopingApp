package com.example.userinformation.dashboard.task.confirmbottomsheetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.userinformation.R
import com.example.userinformation.textbottomsheetdialogbox.CustomTextBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ConfirmBottomSheetDialog:BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_confirmation, container, false)
        val yesBtn = rootView.findViewById<AppCompatButton>(R.id.yesBtn)
        val editBtn = rootView.findViewById<AppCompatButton>(R.id.editBtn)
        val cancelBtn = rootView.findViewById<TextInputLayout>(R.id.crossBtn)
        yesBtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {

                dismiss()
            }
        })
        editBtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {

                dismiss()
            }

        })
        cancelBtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
            }


        })
        return rootView
    }

    companion object{
        fun ConfirmBottomSheetDialogInstance() :ConfirmBottomSheetDialog{
            return ConfirmBottomSheetDialog()
        }
    }
}