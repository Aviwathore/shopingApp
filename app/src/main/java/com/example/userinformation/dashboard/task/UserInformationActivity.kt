package com.example.userinformation.dashboard.task

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.View.OnTouchListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.dashboard.task.confirmbottomsheetdialog.ConfirmBottomSheetDialog
import com.example.userinformation.databinding.ActivityUserInformationBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class UserInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInformationBinding
        private var calendar = Calendar.getInstance(TimeZone.GMT_ZONE)
    private lateinit var editFirstName: TextInputEditText
    private lateinit var editLastName: TextInputEditText
    private lateinit var editDate: MaterialAutoCompleteTextView
    private lateinit var editAddress: TextInputEditText
    private lateinit var editState: AutoCompleteTextView
    private lateinit var editPostal: AutoCompleteTextView
    private lateinit var editCountry: AutoCompleteTextView
    private lateinit var radioButton: RadioGroup
    private lateinit var maleRadioButton :RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var editContact : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {

            startActivity(Intent(this, DashBoardActivity::class.java))
        }

        binding.btnContinue.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                if (TextUtils.isEmpty(editFirstName.text.toString())) {
                    editFirstName.error = "First Name Is Required"
//                    editFirstName.setBackgroundColor(Color.WHITE)
//
//                    val errorColor = Color.parseColor("#FFE91E63")
//                    editFirstName.setTextColor(errorColor)

                    editFirstName.setBackgroundResource(R.drawable.border_color)
                }else if (!editFirstName.text.toString().matches(Regex("[a-zA-Z]+"))) {
                    editFirstName.error = "Invalid First Name"
                    editFirstName.setBackgroundResource(R.drawable.border_color)
                }
                if (TextUtils.isEmpty(editState.text.toString())){
                    editState.error = "State Is Required"
                    editState.setBackgroundResource(R.drawable.border_color)
                }
                if (TextUtils.isEmpty(editLastName.text.toString())) {
                    editLastName.error = "Last Name Is Required"
                    editLastName.setBackgroundResource(R.drawable.border_color)
                }
                if (TextUtils.isEmpty(editContact.text.toString()) ){
                    editContact.error = "Contact Number Is Required"
                    editContact.setBackgroundResource(R.drawable.border_color)
                }else if (!editContact.text.toString().matches(Regex("\\d{10}"))) {
                    // If contact number does not have 10 digits
                    editContact.error = "Invalid Contact Number"
                    editContact.setBackgroundResource(R.drawable.border_color)
                    return
                }
                if (TextUtils.isEmpty(editCountry.text.toString())) {
                    editCountry.error = "Country Name Is Required"
                    editCountry.setBackgroundResource(R.drawable.border_color)
                }
                if (TextUtils.isEmpty(editPostal.text.toString())) {
                    editPostal.error = "Postal Code Is Required"
                    editPostal.setBackgroundResource(R.drawable.border_color)
                }
                if (TextUtils.isEmpty(editDate.text.toString())) {
                    editDate.error = "Date s Required"
                    binding.txtDateOfBirth.endIconDrawable = null

                    editDate.setBackgroundResource(R.drawable.border_color)
                }
                if (TextUtils.isEmpty(editAddress.text.toString())) {
                    editAddress.error = "Address Is Required"
                    editAddress.setBackgroundResource(R.drawable.border_color)
                }

                maleRadioButton= binding.maleBtn
                femaleRadioButton=binding.FemaleBtn

                radioButton.setOnCheckedChangeListener{_, checked ->
                    val radioButton = findViewById<RadioButton>(checked)
                    val gender = radioButton.text.toString()

////                    when (){
////                        "Male" ->{
////
////                        }
////                        "Female" ->{
////
////                        }else->{
////                            Toast.makeText(this@UserInformationActivity, "PLease Select Gender", Toast.LENGTH_SHORT).show()
////                        }
////                    }

                    if (TextUtils.isEmpty(gender)){
                        Toast.makeText(this@UserInformationActivity, "PLease Select Gender", Toast.LENGTH_SHORT).show()
                    }

                    if (!(TextUtils.isEmpty(editFirstName.text.toString()) || TextUtils.isEmpty(editState.text.toString())
                                || TextUtils.isEmpty(editAddress.text.toString()) || TextUtils.isEmpty(editDate.text.toString())
                                || TextUtils.isEmpty(editPostal.text.toString()) || TextUtils.isEmpty(editCountry.text.toString())
                                || TextUtils.isEmpty(editContact.text.toString()) || TextUtils.isEmpty(editLastName.text.toString()))
                        || TextUtils.isEmpty(gender)){

                        val confirmDialog = ConfirmBottomSheetDialog.ConfirmBottomSheetDialogInstance()
                        confirmDialog.show(supportFragmentManager, "")
                    }
                }
            }


        })
//        // set hint
//
        radioButton = binding.radioGroup
        editLastName = binding.editLastName
        editLastName.hint = getString(R.string.enter_last_name)

        editFirstName = binding.editFirstName
        editFirstName.hint = getString(R.string.enter_first_name)

        editContact = binding.editContact
        editContact.hint = "+91 "

        editDate = binding.editdate
        editDate.hint = getString(R.string._5_march_1995)

        editAddress = binding.editAddress
        editAddress.hint = getString(R.string.no_where_st)

        editState = binding.filledExposedDropdown
        editState.hint = getString(R.string.select_state)

        editPostal = binding.editPostal
        editPostal.hint = getString(R.string._2021)

        editCountry = binding.editCountry
        editCountry.hint = getString(R.string.malaysia)

//        // date picker

        binding.editdate.setOnClickListener {
            showDateOfBirthPicker()
        }

        binding.filledExposedDropdown.setOnFocusChangeListener(object : OnFocusChangeListener {

            override fun onFocusChange(v: View?, hasFocus: Boolean) {
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

                val adapter = ArrayAdapter(
                    this@UserInformationActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    stateArray
                )

                val autoCompleteTextView: AutoCompleteTextView = binding.filledExposedDropdown
                autoCompleteTextView.setAdapter(adapter)

                autoCompleteTextView.setOnClickListener {
                    autoCompleteTextView.showDropDown()
                }
            }
        })

        binding.editPostal.setOnFocusChangeListener(object : OnFocusChangeListener {

            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                val postalArray = arrayOf("2021", "2022", "2023", "2024", "2025", "2026")
                val adapter = ArrayAdapter(
                    this@UserInformationActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    postalArray
                )
                val editPostalTextView: AutoCompleteTextView = binding.editPostal
                editPostalTextView.setAdapter(adapter)

                editPostalTextView.setOnClickListener {
                    editPostalTextView.showDropDown()
                }
            }

        })

        binding.editCountry.setOnFocusChangeListener(object : OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
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
                val adapter = ArrayAdapter(
                    this@UserInformationActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    countryArray

                )
                val countryAutoCompletView: AutoCompleteTextView = binding.editCountry
                countryAutoCompletView.setAdapter(adapter)

                countryAutoCompletView.setOnClickListener {
                    countryAutoCompletView.showDropDown()
                }
            }


        })

    }


//    private fun stateSpinner() {
//        Log.d("Spinner", "statspiner call")
//        val stateArray = resources.getStringArray(R.array.State)
//        Log.d("StateSpinner", "State array size: ${stateArray.size}")
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_dropdown_item_1line, // Using the default layout
//            stateArray
//        )
//        val autoCompleteTextView = binding.filledExposedDropdown as AutoCompleteTextView
//        autoCompleteTextView.setAdapter(adapter)
//        Log.d("StateSpinner", "Adapter set to AutoCompleteTextView")
//    }


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
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.maxDate= System.currentTimeMillis()
        datePicker.show()
    }


}