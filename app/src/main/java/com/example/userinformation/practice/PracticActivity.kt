package com.example.userinformation.practice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityPracticBinding
import com.example.userinformation.informationform.emergency_contact_form.customeadaptor.CustomArrayAdapter

class PracticActivity : AppCompatActivity() {
    private lateinit var binding :ActivityPracticBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val anyRelationshipWithBankStaff = arrayOf(
            "Yes",
            "No"
        )
        val images = arrayOf(
            resources.getDrawable(R.drawable.cloth),
            resources.getDrawable(R.drawable.beauty)
        )

        val customAdapter = CustomArrayAdapter(
            this@PracticActivity,
            anyRelationshipWithBankStaff,
            images
        )

        binding.acTxtAnyRelationship.setAdapter(customAdapter)
//        binding.acTxtAnyRelationship.dropDownWidth = 400
        binding.acTxtAnyRelationship.setOnClickListener{
            binding.acTxtAnyRelationship.showDropDown()
        }
    }
}