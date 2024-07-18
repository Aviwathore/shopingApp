package com.example.userinformation.informationform.customspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.userinformation.R

class CustomSpinnerAdapter(context: Context, private val housingOptions: Array<HousingOption>) :
    ArrayAdapter<HousingOption>(context, R.layout.custom_data_spinner, housingOptions) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = customView(position, convertView, parent)
//        view.setBackgroundResource(R.drawable.spinner_dropdown_background)

//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.spinner_dropdown_container_layout, parent, false)

//        val listView = view.findViewById<ListView>(android.R.id.list)
//        val adapter = DropdownAdapter(context, housingOptions)
//        listView.adapter = adapter

        return view
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return customView(position, convertView, parent)
    }

    private fun customView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val rowView = convertView ?: inflater.inflate(R.layout.custom_data_spinner, parent, false)

        val textView = rowView.findViewById<TextView>(R.id.txt_data_spinner)
        val item = getItem(position)
        textView.text = item?.description

        return rowView
    }
}


//private class DropdownAdapter(context: Context, housingOptions: Array<HousingOption>) :
//    ArrayAdapter<HousingOption>(context, R.layout.custom_spinner_dropdown_item, housingOptions) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val inflater = LayoutInflater.from(context)
//        val rowView = convertView ?: inflater.inflate(R.layout.custom_spinner_dropdown_item, parent, false)
//
//        val textView = rowView.findViewById<TextView>(R.id.textViewDropdownItem)
//        val item = getItem(position)
//        textView.text = item?.description
//
//        return rowView
//    }


