package com.example.userinformation.emergency_contact_form

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityGuardianBinding
import com.example.userinformation.informationform.confirmbottomsheetdialog.successfulStoreInfo.SuccessActivity
import com.example.userinformation.informationform.highlightStar

class EmergencyContactFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuardianBinding

    private val relationshipWithContactArray = arrayOf(
        "Grand Father",
        "Grand Mother",
        "Father",
        "Mother",
        "Brother",
        "Sister"
    )

    private val anyRelationshipWithBankStaff = arrayOf(
        "Yes",
        "No"
    )
    private val bankStaffRelation = arrayOf(
        "Siblings",
        "Brother",
        "Sister",
        "Uncle"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuardianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mandatoryFiled()
        editTextHint()

        setUpDropdownAdapters()

        binding.imgBackButton.setOnClickListener(this)
        binding.acTxtRelationship.setOnClickListener(this)
        binding.acTxtAnyRelationship.setOnClickListener(this)
        binding.acTextRelation.setOnClickListener(this)
        binding.acBtnNext.setOnClickListener(this)

        binding.acTxtAnyRelationship.setOnItemClickListener { parent, view, position, id ->
            checkVisibility(position)
        }

        isCheckBoxClicked()

        binding.ilEmergencyContactName.setEndIconOnClickListener {
            showEmergencyDialog()
        }
    }

    private fun checkVisibility(position: Int) {
        val selectedOption = anyRelationshipWithBankStaff[position]

        if (selectedOption == "No") {
            textColor()
            removeMandatoryFiled()
            enableField(false)

        } else {
            enableField(true)
            binding.editContactNumber.isEnabled = true
            binding.editEmergencyContactName.isEnabled = true
            binding.acTxtRelationship.isEnabled = true
            mandatoryFiled()
            highlightTextColor()
        }


    }

    private fun highlightTextColor() {
        binding.txtRelation.setTextColor(Color.BLACK)
        binding.txtBankStaffName.setTextColor(Color.BLACK)
        binding.txtBankStaffMobile.setTextColor(Color.BLACK)
    }

    private fun checkValidationForAllFields(): Boolean {
        var emptyField = false
        val someField = listOf(
            binding.editBankStaffMobile to getString(R.string.bank_staff_mobile_error),
            binding.editBankStaffName to getString(R.string.bank_staff_name_error),
            binding.acTextRelation to getString(R.string.relationship_is_required),
        )
        val extraFields = listOf(
            binding.editContactNumber to getString(R.string.contact_digit_error),
            binding.editEmergencyContactName to getString(R.string.emergency_contact_error),
            binding.acTxtRelationship to getString(R.string.relationship_is_required)
        )
        val allFields = someField + extraFields

        someField.forEach { (field, errorMessage) ->
            val inputField = field.text.toString().trim()
            if (inputField.isEmpty() && field.isEnabled) {
                field.error = errorMessage
                field.setBackgroundResource(R.drawable.border_color)
                removeEndIconDrawable()
                emptyField = true
            }
        }

        allFields.forEach { (field, errorMessage) ->
            val inputField = field.text.toString().trim()
            if (inputField.isEmpty() && field.isEnabled) {
                field.error = errorMessage
                field.setBackgroundResource(R.drawable.border_color)
                removeEndIconDrawable()
                binding.ilEmergencyContactName.endIconDrawable = null
                emptyField = true
            }

            if (binding.editContactNumber.length() != 10) {
                binding.editContactNumber.setBackgroundResource(R.drawable.border_color)
                binding.editContactNumber.error = getString(R.string.contact_digit_error)
                emptyField = true
            }
            if (binding.editBankStaffMobile.length() != 10 && field.isEnabled) {
                binding.editBankStaffMobile.setBackgroundResource(R.drawable.border_color)
                binding.editBankStaffMobile.error = getString(R.string.contact_digit_error)
                emptyField = true
            }

        }
        return !emptyField
    }

    private fun removeEndIconDrawable() {
        binding.ilAnyRelationship.endIconDrawable = null
        binding.ilRelation.endIconDrawable = null
        binding.ilRelationship.endIconDrawable = null
    }

    private fun enableField(enableFields: Boolean) {
        val fields = listOf(
            binding.acTextRelation,
            binding.editBankStaffName,
            binding.editBankStaffMobile
        )

        fields.forEach { field ->
            field.isEnabled = enableFields
        }
        binding.editBankStaffName.error = null
        binding.editBankStaffMobile.error = null
        binding.acTextRelation.error = null
        binding.ilEmergencyContactName.endIconDrawable = null
    }

    private fun isCheckBoxClicked() {
        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.acBtnNext.isEnabled = true
                binding.acBtnNext.setBackgroundResource(R.drawable.btn_crimson)
            } else {
                binding.acBtnNext.isEnabled = false
                binding.acBtnNext.setBackgroundResource(R.drawable.btn_gray)
            }

        }

    }

    private fun setUpDropdownAdapters() {

        val adapter = ArrayAdapter(
            this@EmergencyContactFormActivity,
            android.R.layout.simple_dropdown_item_1line,
            relationshipWithContactArray
        )

        binding.acTxtRelationship.setAdapter(adapter)

        val anyRelationshipAdaptor = ArrayAdapter(
            this@EmergencyContactFormActivity,
            android.R.layout.simple_dropdown_item_1line,
            anyRelationshipWithBankStaff
        )

        binding.acTxtAnyRelationship.setAdapter(anyRelationshipAdaptor)

        val staffRelationAdapter = ArrayAdapter(
            this@EmergencyContactFormActivity,
            android.R.layout.simple_dropdown_item_1line,
            bankStaffRelation
        )
        binding.acTextRelation.setAdapter(staffRelationAdapter)
    }

    private fun showEmergencyDialog() {
        val builder = AlertDialog.Builder(this)
        val dialog: View = layoutInflater.inflate(R.layout.emergency_contact_dialog, null)
        builder.setView(dialog)
        val dialogShow = builder.create()
        dialogShow.show()
    }


    private fun removeMandatoryFiled() {
        highlightStar(binding.txtBankStaffMobile, Color.LTGRAY)
        highlightStar(binding.txtRelation, Color.LTGRAY)
        highlightStar(binding.txtBankStaffName, Color.LTGRAY)
    }

    private fun textColor() {
        binding.txtRelation.setTextColor(Color.LTGRAY)
        binding.txtBankStaffMobile.setTextColor(Color.LTGRAY)
        binding.txtBankStaffName.setTextColor(Color.LTGRAY)
    }

    private fun editTextHint() {
        binding.editContactNumber.hint = getString(R.string.contact_number_hint)
        binding.editEmergencyContactName.hint = getString(R.string.emergency_name_hint)
        binding.acTxtRelationship.hint = getString(R.string.relationship_hint)
        binding.editBankStaffName.hint = getString(R.string.bank_staff_full_name_hint)
        binding.editBankStaffMobile.hint = getString(R.string.bank_staff_mobile_number_hint)
        binding.acTextRelation.hint = getString(R.string.enter_relation_hint)
    }

    private fun mandatoryFiled() {
        highlightStar(binding.txtAnyRelationship, Color.RED)
        highlightStar(binding.txtContactNumber, Color.RED)
        highlightStar(binding.txtRelationship, Color.RED)
        highlightStar(binding.txtEmergencyContactName, Color.RED)
        highlightStar(binding.txtBankStaffMobile, Color.RED)
        highlightStar(binding.txtRelation, Color.RED)
        highlightStar(binding.txtBankStaffName, Color.RED)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgBackButton -> finish()
            R.id.acTxtRelationship -> showRelationshipDropdown()
            R.id.acTxtAnyRelationship -> binding.acTxtAnyRelationship.showDropDown()
            R.id.acTextRelation -> {
                binding.acTextRelation.error = null
                showRStaffRelationDropDown()
                binding.acTextRelation.requestFocus()
            }

            R.id.acBtnNext -> onNextButton()
            else -> {}
        }
    }

    private fun onNextButton() {
        if (checkValidationForAllFields()) {

            startActivity(Intent(this, SuccessActivity::class.java))
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }



    private fun showRStaffRelationDropDown() {
        binding.acTextRelation.showDropDown()
        binding.acTextRelation.error = null
    }

    private fun showRelationshipDropdown() {
        binding.acTxtRelationship.showDropDown()
        binding.acTxtRelationship.error = null
    }
}