package com.example.userinformation.dashboard.task

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.dashboard.task.confirmbottomsheetdialog.ConfirmBottomSheetDialog
import com.example.userinformation.databinding.ActivityUserInformationBinding
import java.util.Locale

class UserInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInformationBinding
    private var calendar = Calendar.getInstance(TimeZone.GMT_ZONE)
    private var confirm: ConfirmBottomSheetDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {

            startActivity(Intent(this, DashBoardActivity::class.java))
        }
        // hint
        binding.editFirstName.hint = getString(R.string.first_name)
        binding.editLastName.hint = getString(R.string.enter_last_name)
        binding.editContact.hint = "+91 "
        binding.editdate.hint = getString(R.string._5_march_1995)
        binding.editAddress.hint = getString(R.string.no_where_st)
        binding.filledExposedDropdown.hint = getString(R.string.select_state)
        binding.editPostal.hint = getString(R.string._2021)
        binding.editCountry.hint = getString(R.string.malaysia)

        // date picker
        binding.editdate.setOnClickListener {
            showDateOfBirthPicker()
        }
        // state picker
        val stateArray = arrayOf(
            "Assam",
            "Bihar",
            "Karnataka",
            "Goa",
            "Haryana",
            "Nagaland",
            "Ladakh",
            "Maharashtra",
            "Kerala",
            "Jharkhand",
            "Punjab",
            "Manipur",
            "Lakshadweep"
        )
        // set to adapter
        val adapter = ArrayAdapter(
            this@UserInformationActivity, android.R.layout.simple_dropdown_item_1line, stateArray
        )
        // set to inputFiled
        binding.filledExposedDropdown.setAdapter(adapter)

        binding.filledExposedDropdown.onFocusChangeListener =
            View.OnFocusChangeListener { _, focus ->
                if (focus) {
                    binding.filledExposedDropdown.showDropDown()
                }
                binding.filledExposedDropdown.error = null
            }
        // Postal picker
        val postalArray = arrayOf(
            "2021", "2022", "2023", "2024", "2025", "2026"
        )

        // set to adapter
        val postalAdaptor = ArrayAdapter(
            this@UserInformationActivity, android.R.layout.simple_dropdown_item_1line, postalArray
        )
        // set to inputFiled
        binding.editPostal.setAdapter(postalAdaptor)

        binding.editPostal.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                binding.editPostal.showDropDown()
                binding.editPostal.error = null
            }
        }

        // Country
        val countryArray = arrayOf(
            "Armenia",
            "Australia",
            "Bangladesh",
            "China",
            "Afghanistan",
            "India",
            "Malaysia",
            "Barbados"
        )
        val countryAdapter = ArrayAdapter(
            this@UserInformationActivity, android.R.layout.simple_dropdown_item_1line, countryArray
        )
        binding.editCountry.setAdapter(countryAdapter)

        binding.editCountry.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                binding.editCountry.showDropDown()
            }
            binding.editCountry.error = null
        }

        binding.maleBtn.setOnCheckedChangeListener { comp, b ->
            binding.radioGroup.background = null
        }

        binding.FemaleBtn.setOnCheckedChangeListener { comp, b ->
            binding.radioGroup.background = null
        }

        binding.btnContinue.setOnClickListener {
            if (checkValidation()) {
                if (confirm != null && confirm!!.isVisible) {
                    confirm!!.dismiss()
                } else {
                    confirm = ConfirmBottomSheetDialog.ConfirmBottomSheetDialogInstance()
                    confirm!!.show(supportFragmentManager, "")
                }
            }
        }
    }


    private fun showDateOfBirthPicker() {
        val datePicker = DatePickerDialog(
            this, { DatePicker, year, monthOfYear, dayOfMonth ->

                // hold selected date
                val selectedDate = Calendar.getInstance()

                //set date which is received from datePicker
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // date format
                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

                // selected date into string
                val formatDate = dateFormat.format(selectedDate.time)

                binding.editdate.setText(formatDate)
                binding.editdate.error = null
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.maxDate = System.currentTimeMillis()
        datePicker.show()
    }

    private fun checkValidation(): Boolean {
        var emptyFiled = false

        val filedData = listOf(
            binding.editFirstName to "First name is required",
            binding.editLastName to "Last name is required",
            binding.editContact to "contact number is required",
            binding.editdate to "Date is required",
            binding.editAddress to "Address is required",
            binding.filledExposedDropdown to "State is required",
            binding.editPostal to "Postal is required",
            binding.editCountry to "Country is required"
        )

        filedData.forEach { (inputFiled, errorMessage) ->
            val filed = inputFiled.text.toString()
            if (filed.isEmpty()) {
                inputFiled.error = errorMessage
                inputFiled.setBackgroundResource(R.drawable.border_color)
                binding.txtDateOfBirth.endIconDrawable = null
                binding.txtInputState.endIconDrawable = null
                binding.txtPostalInput.endIconDrawable = null
                binding.txtCountryInput.endIconDrawable = null

                emptyFiled = true
            } else {
                inputFiled.error = null
            }
        }

        if (!binding.maleBtn.isChecked && !binding.FemaleBtn.isChecked) {
            binding.radioGroup.setBackgroundResource(R.drawable.border_color)
            Toast.makeText(this@UserInformationActivity, "Please Select Gender", Toast.LENGTH_SHORT)
                .show()
            emptyFiled = true
        }
        return !emptyFiled
    }

//    private fun checkGenderValidation(): Boolean {
//
//      else{
//            binding.radioGroup.background = null
//        }
//
//        return true
//    }

//    private fun checkValidation(): Boolean {
//        var emptyFiled = false
//
//        if (TextUtils.isEmpty(binding.editFirstName.text.toString())) {
//            emptyFiled = true
//            binding.editFirstName.error = "First Name Is Required"
//            binding.editFirstName.setBackgroundResource(R.drawable.border_color)
//        }
//        if (TextUtils.isEmpty(binding.editLastName.text.toString())) {
//            emptyFiled = true
//            binding.editLastName.error = "Last name is required"
//            binding.editLastName.setBackgroundResource(R.drawable.border_color)
//        }
//        if (TextUtils.isEmpty(binding.editContact.text.toString())) {
//            emptyFiled = true
//            binding.editContact.error = "contact number is required"
//            binding.editContact.setBackgroundResource(R.drawable.border_color)
//        }
//        if (TextUtils.isEmpty(binding.editdate.text.toString())) {
//            emptyFiled = true
//            binding.editdate.error = "Date is required"
//            binding.editdate.setBackgroundResource(R.drawable.border_color)
//            binding.txtDateOfBirth.endIconDrawable = null
//        }
//        if (!binding.maleBtn.isClickable || !binding.FemaleBtn.isClickable) {
//            Toast.makeText(this@UserInformationActivity, "Please select gender", Toast.LENGTH_SHORT)
//                .show()
//        }
//        if (TextUtils.isEmpty(binding.editAddress.text.toString())) {
//            emptyFiled = true
//            binding.editAddress.error = "Address is required"
//            binding.editAddress.setBackgroundResource(R.drawable.border_color)
//        }
//        if (TextUtils.isEmpty(binding.filledExposedDropdown.text.toString())) {
//            emptyFiled = true
//            binding.filledExposedDropdown.error = "State is required"
//            binding.filledExposedDropdown.setBackgroundResource(R.drawable.border_color)
//            binding.txtInputState.endIconDrawable = null
//        }
//        if (TextUtils.isEmpty(binding.editPostal.text.toString())) {
//            emptyFiled = true
//            binding.editPostal.error = "Postal is required"
//            binding.editPostal.setBackgroundResource(R.drawable.border_color)
//            binding.txtPostalInput.endIconDrawable = null
//        }
//        if (TextUtils.isEmpty(binding.editCountry.text.toString())) {
//            emptyFiled = true
//            binding.editCountry.error = "Country is required"
//            binding.editCountry.setBackgroundResource(R.drawable.border_color)
//            binding.txtCountryInput.endIconDrawable = null
//        }
//        return !emptyFiled
//    }


}