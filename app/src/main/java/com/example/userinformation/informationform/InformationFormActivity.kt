package com.example.userinformation.informationform

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityInformationFormBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.informationform.confirmbottomsheetdialog.ConfirmBottomSheetDialog
import com.example.userinformation.informationform.confirmbottomsheetdialog.CustomCountryArrayAdapter
import com.example.userinformation.informationform.customspinner.CustomSpinnerAdapter
import com.example.userinformation.informationform.customspinner.HousingOption
import com.example.userinformation.informationform.model.YourInformationDataClass
import com.google.gson.Gson
import java.util.Locale


class InformationFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInformationFormBinding
    private var calendar = Calendar.getInstance(TimeZone.GMT_ZONE)
    private var confirm: ConfirmBottomSheetDialog? = null
    private lateinit var dbHelper: ProductDBHelper
    private var isDatePickerShown = false
    private lateinit var images: Array<Drawable>
    private lateinit var spinner: Spinner
    private var selectedId: String = ""

    private val stateArray = arrayOf(
        "Assam",
        "Bihar",
        "Karnataka",
        "Goa",
        "Haryana",
        "Jharkhand",
        "Kerala",
        "Ladakh",
        "Lakshadweep",
        "Maharashtra",
        "Manipur",
        "Nagaland",
        "Punjab"
    )


    private val countryArray = arrayOf(
        "Afghanistan",
        "Armenia",
        "Australia",
        "Bangladesh",
        "Barbados",
        "China",
        "India",
        "Malaysia"

    )
    private val postalArray = arrayOf(
        "2021", "2022", "2023", "2024", "2025", "2026"
    )

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInformationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = ProductDBHelper(this)
        editTextHint()
        mandatoryFieldHighlighter()

        imagesArray()

        setUpDropdownAdapters()
        selectedOnClickListener()

        allOnFocusChangeListener()

        addTextWatcher()

        aadhaarTextWatcher()
        spinner = binding.editSpinner
        customDataSpinner()
        spinnerItemSelected()

        binding.editPostal.setOnClickListener{
            binding.editPostal.showDropDown()
            binding.editPostal.setBackgroundResource(R.drawable.black_mobile_border)
            binding.editPostal.error = null
        }

        val tableSize = dbHelper.getTableSize()
        Log.d("INFORMATION_TABLE_SIZE", "Size of: $tableSize bytes")

        val contacts = dbHelper.getAllYourInfoFormDetails()
        for (contact in contacts) {
            Log.d("INFORMATION_DETAILS", contact.toString())
        }
    }

    private fun selectedOnClickListener() {
        binding.backButton.setOnClickListener(this)
        binding.editDateOfBirth.setOnClickListener(this)
        binding.editState.setOnClickListener(this)
        binding.editCountry.setOnClickListener(this)
        binding.btnContinue.setOnClickListener(this)
        binding.rdMale.setOnClickListener(this)
        binding.rdFemale.setOnClickListener(this)
    }

    private fun addTextWatcher() {
        addTextWatcher(binding.editFirst, R.drawable.border_color, R.drawable.black_mobile_border)
        addTextWatcher(binding.editLast, R.drawable.border_color, R.drawable.black_mobile_border)
        addTextWatcher(binding.editEmail, R.drawable.border_color, R.drawable.black_mobile_border)
        mobileAddTextWatcher()
    }

    private fun spinnerItemSelected(){
        Log.d("spinnerItemSelected", "CALL SPINNER FUN")
        this.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val selectedData: HousingOption =
                    parent?.getItemAtPosition(position) as HousingOption
                selectedId = selectedData.id
//                selectedData[position]
//                Log.d("selectedData", "============= $selectedData")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    private fun aadhaarTextWatcher() {
        binding.editReenterAadhaar.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before == 0 && count == 0) {
                    if (start >= 0) {
                        binding.editReenterAadhaar.setSelection(start)
                    }
                    return
                }
                if (binding.editReenterAadhaar.length() != 14) {
                    binding.editReenterAadhaar.setBackgroundResource(R.drawable.border_color)
                } else {
                    binding.editReenterAadhaar.setBackgroundResource(R.drawable.black_mobile_border)
                }

                val cleanText = s.toString().replace("-", "")
                val formattedText = StringBuilder()

                var index = 0
                for (element in cleanText) {
                    formattedText.append(element)
                    index++
                    if (index % 4 == 0 && index < 12) {
                        formattedText.append("-")
                    }
                }
                val newCursorPosition = when {
                    count > before -> {
                        14
                    }
                    else -> {
                        start - (before / 4)

                    }
                }

                binding.editReenterAadhaar.removeTextChangedListener(this)
                binding.editReenterAadhaar.setText(formattedText)

                binding.editReenterAadhaar.setSelection(
                    minOf(newCursorPosition, formattedText.length)
                )

                binding.editReenterAadhaar.addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun mobileAddTextWatcher() {
        binding.editMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "onTextChanged: " + binding.editMobile.length())
                if (binding.editMobile.length() != 10) {
                    binding.linerLayout.setBackgroundResource(R.drawable.border_color)
                } else {
                    binding.linerLayout.setBackgroundResource(R.drawable.black_mobile_border)
                }


            }

            override fun afterTextChanged(s: Editable?) {
                //
            }

        })
    }

    private fun allOnFocusChangeListener() {

        binding.editPostal.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editPostal.showDropDown()
                binding.editPostal.setBackgroundResource(R.drawable.black_mobile_border)
                binding.editPostal.error = null
            }
        }

        binding.editCountry.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showCountryDropDown()
                binding.editCountry.setBackgroundResource(R.drawable.black_mobile_border)
                binding.editCountry.error =null
            }
        }
        binding.editState.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editState.showDropDown()
                binding.editState.setBackgroundResource(R.drawable.black_mobile_border)
                binding.editState.error = null
            }
        }
        binding.editMobile.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.linerLayout.setBackgroundResource(R.drawable.black_mobile_border)
            }
        }
    }

    private fun addTextWatcher(editText: EditText, emptyDrawableResId: Int, filledDrawableResId: Int) {

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.length() <= 0) {
                    editText.setBackgroundResource(emptyDrawableResId)
                } else {
                    editText.setBackgroundResource(filledDrawableResId)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }


        })
    }

    private fun imagesArray() {
        images = arrayOf(
            ResourcesCompat.getDrawable(resources, R.drawable.afghanistan, null),
            ResourcesCompat.getDrawable(resources, R.drawable.armenia, null),
            ResourcesCompat.getDrawable(resources, R.drawable.australia, null),
            ResourcesCompat.getDrawable(resources, R.drawable.bangladesh, null),
            ResourcesCompat.getDrawable(resources, R.drawable.barbados, null),
            ResourcesCompat.getDrawable(resources, R.drawable.china, null),
            ResourcesCompat.getDrawable(resources, R.drawable.india, null),
            ResourcesCompat.getDrawable(resources, R.drawable.malaysia, null)

        ).filterNotNull().toTypedArray()
    }

    private fun customDataSpinner() {
        val json = """
      [{"id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aef","code": null,"description": "Home owner","cbs_code": null,"ordering": 1,"sub_description": "Inheritance/not under mortgage"}, {"id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aeh","code": null,"description": "Home owner","cbs_code": null,"ordering": 2,"sub_description": "Still under mortgage"}, {"id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aeg","code": null, "description": "Living with parents/relatives","cbs_code": null,"ordering": 3,"sub_description": null}, {"id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aei", "code": null,"description": "Tenant","cbs_code": null,"ordering": 4,"sub_description": null}, {"id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aej","code": null,"description": "Others", "cbs_code": null,"ordering": 5, "sub_description": "Government quarters, company hostel, etc." }]
    """.trimIndent()

        val housingOptions = Gson().fromJson(json, Array<HousingOption>::class.java)
        val customSpinnerAdapter =
            CustomSpinnerAdapter(this@InformationFormActivity, housingOptions)
        spinner.adapter = customSpinnerAdapter

    }

    private fun setUpDropdownAdapters() {
        val adapter = ArrayAdapter(
            this@InformationFormActivity, android.R.layout.simple_dropdown_item_1line, stateArray
        )
        binding.editState.setAdapter(adapter)

        val postalAdaptor = ArrayAdapter(
            this@InformationFormActivity, android.R.layout.simple_dropdown_item_1line, postalArray
        )

        binding.editPostal.setAdapter(postalAdaptor)

        val customAdapter = CustomCountryArrayAdapter(
            this@InformationFormActivity,
            countryArray,
            images
        )

        binding.editCountry.setAdapter(customAdapter)
    }

    private fun editTextHint() {
        binding.editFirst.hint = getString(R.string.first_name_hint)
        binding.editLast.hint = getString(R.string.last_name_hint)
        binding.editCountry.hint = getString(R.string.country_hint)
        binding.editEmail.hint = getString(R.string.email_hint)
        binding.editMobile.hint = getString(R.string.mobile_number_hint)
        binding.editDateOfBirth.hint = getString(R.string.date_of_birth_hint)
        binding.editPostal.hint = getString(R.string.postal_hint)
        binding.editState.hint = getString(R.string.state_hint)
        binding.editAddress.hint = getString(R.string.address_hint)
        binding.editReenterAadhaar.hint = getString(R.string.aadhaar_number_hint)

    }

    private fun mandatoryFieldHighlighter() {
        highlightStar(binding.txtFirstName, Color.RED)
        highlightStar(binding.txtReenterAadhaar, Color.RED)
        highlightStar(binding.txtCountry, Color.RED)
        highlightStar(binding.txtDate, Color.RED)
        highlightStar(binding.txtEmail, Color.RED)
        highlightStar(binding.txtGender, Color.RED)
        highlightStar(binding.txtLast, Color.RED)
        highlightStar(binding.txtMobile, Color.RED)
        highlightStar(binding.txtPostal, Color.RED)
        highlightStar(binding.txtState, Color.RED)
        highlightStar(binding.txtSpinner, Color.RED)
    }

    private fun getData(): YourInformationDataClass {
        val first = binding.editFirst.text.toString().trim()
        val last = binding.editLast.text.toString().trim()
        val mobile = binding.editMobile.text.toString().trim()
        val genderCheckId = binding.rgGender.checkedRadioButtonId

        val gender = when (genderCheckId) {
            R.id.rdMale -> "Male"
            R.id.rdFemale -> "Female"
            else -> " "
        }
        val state = binding.editState.text.toString()
        val country = binding.editCountry.text.toString()
        val dateOfBirth = binding.editDateOfBirth.text.toString().trim()
        val address = binding.editAddress.text.toString().trim()
        val postal = binding.editPostal.text.toString()
        val email = binding.editEmail.text.toString().trim()
        val aadhaar = binding.editReenterAadhaar.text.toString()
        val selectedData: Any? = binding.editSpinner.selectedItem

        Log.d("DATAA ", "=============$selectedData")
        return YourInformationDataClass(
            firstName = first,
            lastName = last,
            email = email,
            mobileNumber = mobile,
            dateOfBirth = dateOfBirth,
            address = address,
            aadhaarNumber = aadhaar,
            gender = gender,
            state = state,
            postal = postal,
            country = country,
            spinnerData = selectedId
        )
    }

    private fun checkValidation(): Boolean {
        var emptyFiled = false

        val filedData = listOf(
            binding.editFirst to "First name is required",
            binding.editLast to "Last name is required",
            binding.editDateOfBirth to "Date is required",
            binding.editReenterAadhaar to "Re-Enter Aadhaar number is required",
            binding.editState to "State is required",
            binding.editPostal to "Postal is required",
            binding.editCountry to "Country is required",
            binding.editEmail to "Email is required",
        )

        filedData.forEach { (inputFiled, errorMessage) ->
            val filed = inputFiled.text.toString().trim()

            if (filed.isEmpty()) {
                inputFiled.error = errorMessage
                inputFiled.setBackgroundResource(R.drawable.border_color)
                binding.inputDate.endIconDrawable = null
                binding.inputCountry.endIconDrawable = null
                binding.inputPostal.endIconDrawable = null
                binding.inputState.endIconDrawable = null
                emptyFiled = true
            }
        }

        if (binding.editMobile.length() != 10) {
            binding.editMobile.error = "Must be 10 digit mobile number is required"
            binding.linerLayout.setBackgroundResource(R.drawable.border_color)
            emptyFiled = true
        }

        if (!isEmailValidate()) {
            binding.editEmail.error = "Invalid Email"
            binding.editEmail.setBackgroundResource(R.drawable.border_color)
            emptyFiled = true
        }
        if (!binding.rdMale.isChecked && !binding.rdFemale.isChecked) {
            binding.rgGender.setBackgroundResource(R.drawable.border_color)
            emptyFiled = true
        }
        if (binding.editReenterAadhaar.length() != 14) {
            binding.editEmail.setBackgroundResource(R.drawable.border_color)
            binding.editReenterAadhaar.error = "Aadhaar number must be 12 digits long"
            emptyFiled = true
        }

        return !emptyFiled
    }


    private fun isEmailValidate(): Boolean {
        val email = binding.editEmail.text.toString().trim()
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showDateOfBirthPicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            { DatePicker, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                val formatDate = dateFormat.format(selectedDate.time)
                binding.editDateOfBirth.setText(formatDate)
                if (binding.editDateOfBirth.length() <= 0) {
                    binding.editDateOfBirth.setBackgroundResource(R.drawable.border_color)
                } else {
                    binding.editDateOfBirth.error = null
                    binding.editDateOfBirth.setBackgroundResource(R.drawable.black_mobile_border)
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
        datePickerDialog.setOnDismissListener {
            isDatePickerShown = false
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backButton -> finish()
            R.id.btnContinue -> isValidated()
            R.id.editCountry -> showCountryDropDown()
            R.id.editPostal -> showPostalDropDown()
            R.id.editState -> showStateDropDown()
            R.id.editDateOfBirth -> showDaterPicker()
            R.id.rdMale -> isMaleBoxClick()
            R.id.rdFemale -> isFemaleClick()
            else -> {}
        }

    }

    private fun isFemaleClick() {
        if (binding.rdFemale.isChecked) {
            binding.rgGender.setBackgroundResource(android.R.color.transparent)
        }
    }

    private fun isMaleBoxClick() {
        if (binding.rdMale.isChecked) {
            binding.rgGender.setBackgroundResource(android.R.color.transparent)
        }
    }

    private fun showDaterPicker() {
        if (!isDatePickerShown) {
            showDateOfBirthPicker()
            isDatePickerShown = true
        }
    }

    private fun showStateDropDown() {
        binding.editState.showDropDown()
        binding.editState.error = null
    }

    private fun showPostalDropDown() {
        binding.editPostal.showDropDown()
        binding.editPostal.error = null
    }

    private fun showCountryDropDown() {
        binding.editCountry.showDropDown()
        binding.editCountry.error = null
    }

    @SuppressLint("RestrictedApi")
    private fun isValidated() {
        if (checkValidation()) {
            val formData = getData()
            if (confirm != null && confirm!!.isVisible) {
                confirm!!.dismiss()
            } else {
                confirm = ConfirmBottomSheetDialog.getInstance(formData)
                confirm!!.show(supportFragmentManager, "")
            }
        }
    }

}

