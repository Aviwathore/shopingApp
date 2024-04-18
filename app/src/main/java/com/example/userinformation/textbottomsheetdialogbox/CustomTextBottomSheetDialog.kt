package com.example.userinformation.textbottomsheetdialogbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.userinformation.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomTextBottomSheetDialog : BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
        val okayBtn = view.findViewById<AppCompatButton>(R.id.btn_bottom_sheet)
        okayBtn.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
            }

        })
        return view
    }
    companion object {
        fun newInstance(): CustomTextBottomSheetDialog {
            return CustomTextBottomSheetDialog()
        }
    }
}