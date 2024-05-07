package com.example.userinformation.intent.housingoption

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityHousingOptionBinding

import com.google.gson.Gson

class HousingOptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHousingOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHousingOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spinner: Spinner = findViewById(R.id.spinner)

        val json = """
    [{

        "id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aef",

        "code": null,

        "description": "Home owner",

        "cbs_code": null,

        "ordering": 1,

        "sub_description": "Inheritance/not under mortgage"

    }, {

        "id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aeh",

        "code": null,

        "description": "Home owner",

        "cbs_code": null,

        "ordering": 2,

        "sub_description": "Still under mortgage"

    }, {

        "id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aeg",

        "code": null,

        "description": "Living with parents/relatives",

        "cbs_code": null,

        "ordering": 3,

        "sub_description": null

    }, {

        "id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aei",

        "code": null,

        "description": "Tenant",

        "cbs_code": null,

        "ordering": 4,

        "sub_description": null

    }, {

        "id": "8f3a6d9d-eeb2-4ac8-b688-7db4c2158aej",

        "code": null,

        "description": "Others",

        "cbs_code": null,

        "ordering": 5,

        "sub_description": "Government quarters, company hostel, etc."

    }

    ]

        """.trimIndent()

//        val housingOptions = Gson().fromJson(json, Array<HousingOption>::class.java)

        val housingOptions = Gson().fromJson(json, Array<HousingOption>::class.java)
        val descriptions = housingOptions.map { it.description }

        spinner.adapter = ArrayAdapter(
            this@HousingOptionActivity,
            android.R.layout.simple_spinner_item,
            descriptions
        )

    }
}