package com.example.userinformation.informationform.confirmbottomsheetdialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.userinformation.R

class CustomCountryArrayAdapter(
    context: Context,
    private val values: Array<String>,
    private val images: Array<Drawable>
) : ArrayAdapter<String>(context, R.layout.custome_country_spinner, values) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return customView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return customView(position, convertView, parent)
    }

    private fun customView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val rowView = inflater.inflate(R.layout.custome_country_spinner, parent, false)

        val textView = rowView.findViewById<TextView>(R.id.text)
        val imageView = rowView.findViewById<ImageView>(R.id.image)

        if (position in values.indices && position in images.indices) {
            textView.text = values[position]
            imageView.setImageDrawable(images[position])
        }

        return rowView
    }
}
