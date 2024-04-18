package com.example.userinformation.dashboard.task.confirmbottomsheetdialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.userinformation.R
import com.example.userinformation.dashboard.task.confirmbottomsheetdialog.dbHelper.InformationDbHelper
import com.example.userinformation.dashboard.task.confirmbottomsheetdialog.successfulStoreInfo.SuccessActivity
import com.example.userinformation.databinding.ActivityUserInformationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class ConfirmBottomSheetDialog:BottomSheetDialogFragment() {

    private lateinit var binding: ActivityUserInformationBinding
    private lateinit var dbHelper: InformationDbHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_confirmation, container, false)
        val yesBtn = rootView.findViewById<AppCompatButton>(R.id.yesBtn)
        val editBtn = rootView.findViewById<AppCompatButton>(R.id.editBtn)
        val cancelBtn = rootView.findViewById<TextInputLayout>(R.id.crossBtn)
        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        yesBtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {

                dbHelper= context?.let { InformationDbHelper(it) }!!

                val firstName = binding.editFirstName.text.toString()
                val lastName = binding.editLastName.text.toString()
                val address = binding.editAddress.text.toString()
                val state = binding.filledExposedDropdown.text.toString()
                val country = binding.editCountry.text.toString()
                val postal = binding.editPostal.text.toString()

                val saveData = dbHelper.insertInfo(context!!,firstName, lastName, address, state, country, postal)

                if (saveData!=null){
                    startActivity(Intent(context, SuccessActivity::class.java))
                }else{
                    dismiss()
                }
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