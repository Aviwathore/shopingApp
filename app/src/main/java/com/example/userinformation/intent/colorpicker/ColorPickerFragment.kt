package com.example.userinformation.intent.colorpicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import yuku.ambilwarna.AmbilWarnaDialog

class ColorPickerFragment : Fragment() {

    private var selectText: TextView? = null
    private var setColor: AppCompatButton? = null
    private lateinit var pickColor: AppCompatButton
    private  var previewColor :View?=null

    private var defaultColor =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_color_picker, container, false)

        setColor= view.findViewById(R.id.btn_set_color)
        selectText = view.findViewById(R.id.txt_select_color)
        pickColor = view.findViewById(R.id.picker_color)
        previewColor =view.findViewById(R.id.preview_selected_color)

        defaultColor=0

        pickColor.setOnClickListener(
            object : OnClickListener {
                override fun onClick(v: View?) {

                    openColorPickerDialogue()
                }
            })

        setColor?.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                selectText?.setTextColor(defaultColor)
            }
        })

        return view
    }

    private fun openColorPickerDialogue() {
        val colorPickerDialogue = AmbilWarnaDialog(context, defaultColor,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                }

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    defaultColor = color

                    previewColor?.setBackgroundColor(defaultColor)
                }
            })
        colorPickerDialogue.show()
    }

    fun closeFragment() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }

}