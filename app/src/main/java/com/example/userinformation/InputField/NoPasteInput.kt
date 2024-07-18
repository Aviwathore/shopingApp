package com.example.userinformation.InputField

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText

class NoPasteInput : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence {
        // Return null to prevent pasting
        return ""
    }
}

// Apply NoPasteInputFilter to EditText
fun EditText.disablePaste() {
    val inputFilters = filters.toMutableList()
    inputFilters.add(NoPasteInput())
    filters = inputFilters.toTypedArray()

    // Additionally, disable paste action from context menu
    setOnLongClickListener { true }
    setTextIsSelectable(false)
    setTextKeepState(text)

    customSelectionActionModeCallback = object : android.view.ActionMode.Callback {
        override fun onActionItemClicked(
            mode: android.view.ActionMode?,
            item: android.view.MenuItem?
        ): Boolean {
            return false
        }

        override fun onCreateActionMode(
            mode: android.view.ActionMode?,
            menu: android.view.Menu?
        ): Boolean {
            return false
        }

        override fun onPrepareActionMode(
            mode: android.view.ActionMode?,
            menu: android.view.Menu?
        ): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: android.view.ActionMode?) {}
    }
}

// Example usage
// Assuming editText is your EditText view
//editText.disablePaste()
