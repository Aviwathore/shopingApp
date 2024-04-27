package com.example.userinformation.informationform

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityInformationFormBinding
import com.example.userinformation.informationform.confirmbottomsheetdialog.ConfirmBottomSheetDialog
import com.example.userinformation.informationform.dbHelper.FormDBHelper
import com.example.userinformation.informationform.dbHelper.InformationFormDataClass
import java.util.Locale


class InformationFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInformationFormBinding
    private var calendar = Calendar.getInstance(TimeZone.GMT_ZONE)
    private var confirm: ConfirmBottomSheetDialog? = null
    private lateinit var dbHelper: FormDBHelper
    private var isDatePickerShown = false
    private val stateArray = arrayOf(
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


    private val countryArray = arrayOf(
        "Armenia",
        "Australia",
        "Bangladesh",
        "China",
        "Afghanistan",
        "India",
        "Malaysia",
        "Barbados"
    )
    private val postalArray = arrayOf(
        "2021", "2022", "2023", "2024", "2025", "2026"
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInformationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        editTextHint()
        mandatoryFieldHighlighter()
        binding.inputLayoutMobile.setBackgroundResource(R.drawable.black_mobile_border)

        binding.editDateOfBirth.setOnClickListener {
            if (!isDatePickerShown) {
                showDateOfBirthPicker()
                isDatePickerShown = true
            }
        }

        val adapter = ArrayAdapter(
            this@InformationFormActivity, android.R.layout.simple_dropdown_item_1line, stateArray
        )
        binding.editState.setAdapter(adapter)

        binding.editState.setOnClickListener {
            binding.editState.showDropDown()
            binding.editState.error = null
        }


        binding.editPostal.setOnClickListener {
            binding.editPostal.showDropDown()
            binding.editPostal.error = null

        }


        val postalAdaptor = ArrayAdapter(
            this@InformationFormActivity, android.R.layout.simple_dropdown_item_1line, postalArray
        )

        binding.editPostal.setAdapter(postalAdaptor)

        val countryAdapter = ArrayAdapter(
            this@InformationFormActivity, android.R.layout.simple_dropdown_item_1line, countryArray
        )
        binding.editCountry.setAdapter(countryAdapter)

        binding.editCountry.setOnClickListener {
            binding.editCountry.showDropDown()
            binding.editCountry.error = null
        }

        binding.editMobile.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                binding.linerLayout.setBackgroundResource(R.drawable.mobile_border_layout)
            } else {
                binding.linerLayout.setBackgroundResource(R.drawable.black_mobile_border)
            }

        }

        binding.btnContinue.setOnClickListener {
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

        if (!::dbHelper.isInitialized) {
            dbHelper = FormDBHelper(this)
        }
        showFormData()

        binding.editReenterAadhaar.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("BEFORE TEXT CHANGE", "WHAT THINGS ARE DONN BE-FOUR TEXT CHANGE")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before == 0 && count == 0) {
                    if (start >= 0) {
                        binding.editReenterAadhaar.setSelection(start)
                    }
                    return
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
                Log.d("AFTER TEXT CHANGE", "WHAT THINGS ARE DONN AFTER TEXT CHANGE")
            }
        })


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
    }

    private fun showFormData() {
        val formStoreData = dbHelper.getFormData()
        if (formStoreData.isNotEmpty()) {
            val infoData = formStoreData[formStoreData.size - 1]
            binding.editFirst.setText(infoData.first_name)
            binding.editLast.setText(infoData.last_name)
            binding.editMobile.setText(infoData.mobile)
            binding.editPostal.setText(infoData.postal)
            binding.editCountry.setText(infoData.country)
            binding.editState.setText(infoData.state)
            binding.editEmail.setText(infoData.email)
            if (infoData.address.isNotEmpty()) {
                binding.editAddress.setText(infoData.address)
            } else {
                binding.editAddress.setText("")
            }

            binding.editDateOfBirth.setText(infoData.date_of_birth)

            if (infoData.gender == "Male") {
                binding.rdMale.isChecked = true
            } else if (infoData.gender == "Female") {
                binding.rdFemale.isChecked = true
            }
        }
    }


    private fun getData(): InformationFormDataClass {
        dbHelper = FormDBHelper(this)
        val first = binding.editFirst.text.toString().trim()
        val last = binding.editLast.text.toString().trim()
        val mobile = binding.editMobile.text.toString().trim()
        val GenderCheckId = binding.rgGender.checkedRadioButtonId

        val gender = when (GenderCheckId) {
            R.id.rdMale -> "Male"
            R.id.rdFemale -> "Female"
            else -> " "
        }
        val state = binding.editState.text.toString().trim()
        val country = binding.editCountry.text.toString().trim()
        val dateOfBirth = binding.editDateOfBirth.text.toString().trim()
        val address = binding.editAddress.text.toString().trim()
        val postal = binding.editPostal.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()

        return InformationFormDataClass(
            0,
            first,
            last,
            mobile,
            gender,
            state,
            country,
            dateOfBirth,
            address,
            postal,
            email
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
            binding.editEmail to "Email is required"
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
            } else {
                inputFiled.error = null
            }
        }

        if (binding.editMobile.text.toString().isEmpty()) {
            binding.editMobile.error = "Mobile number is required"
            binding.linerLayout.setBackgroundResource(R.drawable.border_color)
            emptyFiled = true
        }
        if (binding.editMobile.length() != 10) {
            binding.editMobile.error = "Must be 10 digit mobile number is required"
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
                binding.editDateOfBirth.error = null
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


    }


}

