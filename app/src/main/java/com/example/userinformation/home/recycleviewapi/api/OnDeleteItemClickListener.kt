package com.example.userinformation.home.recycleviewapi.api

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.widget.DialogTitle
import com.example.userinformation.home.recycleviewapi.model.HomeToDo

interface OnDeleteItemClickListener {
    @SuppressLint("RestrictedApi")
    fun onItemClick(position: Int, homeList: ArrayList<HomeToDo>)
}