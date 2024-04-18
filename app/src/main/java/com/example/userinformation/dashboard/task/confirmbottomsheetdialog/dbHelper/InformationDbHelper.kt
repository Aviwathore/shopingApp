package com.example.userinformation.dashboard.task.confirmbottomsheetdialog.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date

class InformationDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "Information.DB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME= "UserInfo"
        private var KEY_ID = "info_id"
        private var FIRST_NAME ="first_name"
        private var  LAST_NAME = "last_name"
        private var CONTACT = "contact"
        private var GENDER = "gender"
        private var STATE = "state"
        private var COUNTRY ="country"
        private var DATE_OF_BIRTH = "date_of_birth"
        private var ADDRESS = "address"
        private var POSTAL = "postal"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table ${TABLE_NAME}"+
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$FIRST_NAME TEXT ," +
                "$LAST_NAME TEXT," +
                "$CONTACT NUMBER," +
                "$GENDER TEXT," +
                "$STATE TEXT ," +
                "$COUNTRY TEXT ," +
                "$DATE_OF_BIRTH DATE ," +
                "$ADDRESS TEXT," +
                " $POSTAL TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun insertInfo(context: Context, firstName:String, lastName:String, address:String, state:String, postal:String, country:String){
        val dpHelper = InformationDbHelper(context)

        val database = dpHelper.writableDatabase

        val data = ContentValues().apply {

            put(FIRST_NAME, firstName)
            put(LAST_NAME, lastName)
            put(ADDRESS, address)
            put(STATE,state)
            put(POSTAL, postal)
            put(COUNTRY, country)
        }

        database.insert(TABLE_NAME, null, data)

        database.close()
    }
}