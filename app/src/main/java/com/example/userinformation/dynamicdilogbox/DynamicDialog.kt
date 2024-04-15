package com.example.userinformation.dynamicdilogbox

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.userinformation.R

class DynamicDialog : DialogFragment() {
    private var mainTitle : String=""
    private var title: String = ""
    private var id: Int? = null
    private var listener: DynamicDialogInterface? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainTitle = it.getString("mainTitle", "")
            title = it.getString("title", "")
            id = it.getInt("id")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dynamic_dialog_layout, container, false)

        view.findViewById<TextView>(R.id.txt_bottom_sheet_title)
        view.findViewById<TextView>(R.id.txt_bottom_sheet_des).text = title
        view.findViewById<TextView>(R.id.txt_bottom_sheet_id).text = id.toString()

        val okayBtn = view.findViewById<AppCompatButton>(R.id.btn_bottom_sheet_okay)
        val cancel = view.findViewById<AppCompatButton>(R.id.btn_bottom_sheet_cancel)
        okayBtn.setOnClickListener {
            id?.let { listener?.onOkayButtonClicked(it) }
            dismiss()
        }
        cancel.setOnClickListener{
            dismiss()
        }
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation // Optional: Add animation
//        dialog?.window?.setGravity(Gravity.CENTER)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(mainTitle : String , title: String, id: Int, listener: DynamicDialogInterface): DynamicDialog {
            return DynamicDialog().apply {
                arguments = Bundle().apply {
                    putString("mainTitle", mainTitle)
                    putInt("id", id)
                    putString("title", title)
                }
                this.listener= listener
            }
        }
    }

}



