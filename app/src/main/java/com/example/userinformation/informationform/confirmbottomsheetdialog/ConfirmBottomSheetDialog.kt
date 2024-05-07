package com.example.userinformation.informationform.confirmbottomsheetdialog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.userinformation.R
import com.example.userinformation.databinding.DialogConfirmationBinding
import com.example.userinformation.informationform.dbHelper.InformationFormDBHelper
import com.example.userinformation.informationform.dbHelper.YourInformationDataClass
import com.example.userinformation.informationform.emergency_contact_form.EmergencyContactFormActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmBottomSheetDialog(private var formDataClass: YourInformationDataClass) :
    BottomSheetDialogFragment() {

    private lateinit var dbHelper: InformationFormDBHelper

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_confirmation, container, false)
        val binding = DialogConfirmationBinding.bind(rootView)


        val context = requireContext()
        dbHelper = InformationFormDBHelper(context)

        val first = formDataClass.firstName
        val last = formDataClass.lastName
        val mobile = formDataClass.mobileNumber
        val gender = formDataClass.gender
        val state =formDataClass.state
        val country = formDataClass.country
        val dateOfBirth = formDataClass.dateOfBirth
        val address = formDataClass.address
        val postal = formDataClass.postal
        val email = formDataClass.email
        val aadhaar = formDataClass.aadhaarNumber
        val spinnerData = formDataClass.spinnerData
        Log.d("spinnerData", "==============spinner data $spinnerData")


        val data = YourInformationDataClass(
            first,
            last,
            email,
            mobile,
            dateOfBirth,
            address,
            aadhaar,
            gender,
            state,
            postal,
            country,
            spinnerData
        )

        binding.yesBtn.setOnClickListener{


            if (!isDataAlreadyExist(data)) {
                storeDataInDataBase(data)
                dismiss()
            }else{
                startActivity(Intent(context, EmergencyContactFormActivity::class.java))
                dismiss()

            }

        }

        binding.editBtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
            }

        })
        binding.crossBtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
            }

        })
        return rootView
    }

    private fun isDataAlreadyExist(data: YourInformationDataClass): Boolean {
        val database = dbHelper.readableDatabase
        val col = arrayOf("mobile_number")
        val selection = "mobile_number =?"
        val selectionArg = arrayOf(data.mobileNumber)

        val cursor = database.query(
            "information",
            col,
            selection,
            selectionArg,
            null,
            null,
            null
        )
        val dataExist = cursor.count > 0
        cursor.close()
        database.close()
        return dataExist


    }

    private fun storeDataInDataBase(formDataClass: YourInformationDataClass) {
        startActivity(Intent(context, EmergencyContactFormActivity::class.java))
        dbHelper.insertData(formDataClass)
    }
    companion object {
        fun getInstance(formDataClass: YourInformationDataClass): ConfirmBottomSheetDialog {
            return ConfirmBottomSheetDialog(formDataClass)
        }
    }

}