//package com.example.userinformation.monthyearpicker
//import android.R
//import android.app.Activity
//import android.app.DatePickerDialog
//import android.app.DatePickerDialog.OnDateSetListener
//import android.app.Dialog
//import android.os.Bundle
//import android.view.View
//import android.widget.DatePicker
//import android.widget.EditText
//import java.util.Calendar
//
//
////class MonthYearPicker {
////}
//
//
//open class MainActivity : Activity() {
//    private var mYear = 0
//    private var mMonth = 0
//    private var mDay = 0

//    private var etPickADate: EditText? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main)
//        etPickADate = findViewById<View>(R.id.et_datePicker) as EditText
//        etPickADate!!.setOnClickListener { showDialog(DATE_DIALOG_ID) }
//
//        val c = Calendar.getInstance()
//        mYear = c[Calendar.YEAR]
//        mMonth = c[Calendar.MONTH]
//    }
//
//    var mDateSetListner: OnDateSetListener =
//        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            mYear = year
//            mMonth = monthOfYear
//            mDay = dayOfMonth
//            updateDate()
//        }
//
//    override fun onCreateDialog(id: Int): Dialog {
//        when (id) {
//            DATE_DIALOG_ID -> {
//                /*
//   * return new DatePickerDialog(this, mDateSetListner, mYear, mMonth,
//   * mDay);
//   */
//                val datePickerDialog = this.customDatePicker()
//                return datePickerDialog
//            }
//        }
//        return null
//    }
//
//    private fun updateDate() {
//        val localMonth = (mMonth + 1)
//        val monthString = if (localMonth < 10) "0$localMonth" else localMonth.toString()
//        val localYear = mYear.toString().substring(2)
//        etPickADate!!.setText(
//            StringBuilder() // Month is 0 based so add 1
//                .append(monthString).append("/").append(localYear).append(" ")
//        )
//        showDialog(DATE_DIALOG_ID)
//    }
//
//    private fun customDatePicker(): DatePickerDialog {
//        val dpd = DatePickerDialog(
//            this, mDateSetListner,
//            mYear, mMonth, mDay
//        )
//        try {
//            val datePickerDialogFields = dpd.javaClass.declaredFields
//            for (datePickerDialogField in datePickerDialogFields) {
//                if (datePickerDialogField.name == "mDatePicker") {
//                    datePickerDialogField.isAccessible = true
//                    val datePicker = datePickerDialogField[dpd] as DatePicker
//                    val datePickerFields = datePickerDialogField.type
//                        .declaredFields
//                    for (datePickerField in datePickerFields) {
//                        if ("mDayPicker" == datePickerField.name || "mDaySpinner" == datePickerField
//                                .name
//                        ) {
//                            datePickerField.isAccessible = true
//                            var dayPicker = Any()
//                            dayPicker = datePickerField[datePicker]
//                            (dayPicker as View).visibility = View.GONE
//                        }
//                    }
//                }
//            }
//        } catch (ex: Exception) {
//        }
//        return dpd
//    }
//
//    companion object {
//        const val DATE_DIALOG_ID: Int = 1
//    }
//}