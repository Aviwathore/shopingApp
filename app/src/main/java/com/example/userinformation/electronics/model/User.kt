package com.example.userinformation.electronics.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_info")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?
)
