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
import com.example.userinformation.emergency_contact_form.EmergencyContactFormActivity
import com.example.userinformation.informationform.InformationFormActivity
import com.example.userinformation.informationform.dbHelper.FormDBHelper
import com.example.userinformation.informationform.dbHelper.InformationFormDataClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmBottomSheetDialog(private val formDataClass: InformationFormDataClass):BottomSheetDialogFragment() {

    private lateinit var dbHelper: FormDBHelper

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_confirmation, container, false)
        val binding = DialogConfirmationBinding.bind(rootView)


        val context = requireContext()
        dbHelper = FormDBHelper(context)

        val first = formDataClass.first_name
        Log.d("First", first)
        val last =formDataClass.last_name
        val mobile =formDataClass.mobile
        val gender = formDataClass.gender
        val state =formDataClass.state
        val country = formDataClass.country
        val dateOfBirth = formDataClass.date_of_birth
        val address = formDataClass.address
        Log.d("ADDRESS", address)
        val postal = formDataClass.postal
        val email = formDataClass.email

        binding.yesBtn.setOnClickListener{
            val data = InformationFormDataClass(0,first,last,mobile,gender,state,country,dateOfBirth,address,postal,email)
            storeDataInDataBase(data)
            Log.d("AFTER CLICK ON YES BUTTON","DATA INSERT")
            dismiss()
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

    private fun storeDataInDataBase(formDataClass: InformationFormDataClass) {
        val returnId =dbHelper.insertData(formDataClass)

        if (returnId!=1L){
            Log.d("Data Insertion", "Data inserted successfully")
            startActivity(Intent(context, EmergencyContactFormActivity::class.java))
        }else{
            startActivity(Intent(context, InformationFormActivity::class.java))
        }
    }

    companion object{
        fun getInstance(formDataClass: InformationFormDataClass) : ConfirmBottomSheetDialog {
            return ConfirmBottomSheetDialog(formDataClass)
        }
    }
}