package com.example.userinformation.timepicker

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityTimePickerBinding
import com.google.android.material.button.MaterialButton

class TimePicker : AppCompatActivity() {
    private lateinit var binding: ActivityTimePickerBinding
    private lateinit var pickTime: MaterialButton
    private lateinit var selectTime: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_picker)

        binding = ActivityTimePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pickTime = findViewById(R.id.pickme)
        selectTime = findViewById(R.id.selectTime)

        pickTime.setOnClickListener {

            val clock = Calendar.getInstance()

            val hour = clock.get(Calendar.HOUR_OF_DAY)
            val minute = clock.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    val time = "$hourOfDay : $minute"
                    pickTime.text = time
                },
                hour,
                minute,
                false

            )
            pickTime.setOnClickListener {
                timePicker.show()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
