package com.example.userinformation.informationform.emergency_contact_form.customeadaptor

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.userinformation.R

class CustomArrayAdapter(
    context: Context,
    private val values: Array<String>,
    private val images: Array<Drawable>
) : ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, values) {

    override fun getCount(): Int {
//        Log.d("Array Sizes", "Values size: ${values.size}, Images size: ${images.size}")

        return values.size
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return customView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return customView(position, convertView, parent)
    }

    private fun customView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.custome_any_relation_spinner, parent, false)

        val textView = rowView.findViewById<TextView>(R.id.text)
//        Log.d("VIEW ID", "================$textView")
        val imageView = rowView.findViewById<ImageView>(R.id.image)
//        Log.d("IMAGE VIEW", "=================$imageView")

        if (position in values.indices && position in images.indices) {
            textView.text = values[position]
//            Log.d("TEXT", "========$textView")
            imageView.setImageDrawable(images[position])
        }

        return rowView
    }
}
