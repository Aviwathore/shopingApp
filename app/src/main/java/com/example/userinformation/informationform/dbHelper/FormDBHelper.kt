package com.example.userinformation.informationform.dbHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.userinformation.informationform.emergency_contact_form.emergencycontactformdhbelper.EmergencyContactDataClass

class FormDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME ="form.DB"
        private const val DATABASE_VERSION = 4
        private const val TABLE_NAME = "Information"
        private var KEY_ID = "info_id"
        private var FIRST_NAME = "first_name"
        private var LAST_NAME = "last_name"
        private var MOBILE_NUMBER = "mobile"
        private var GENDER = "gender"
        private var STATE = "state"
        private var COUNTRY = "country"
        private var DATE_OF_BIRTH = "date_of_birth"
        private var ADDRESS = "address"
        private var POSTAL = "postal"
        private var EMAIL = "email"

        private var EMERGENCY_CONTACT_NAME= "emergency_contact"
        private var MOBILE ="mobile_number"
        private var RELATIONSHIP="relationship"
        private var RELATION_WITH_BANK_STAFF ="relation_with_bank_staff"
        private var BANK_STAFF_NAME ="bank_staff_name"
        private var Is_Any_Relation = "any_relation"
        private  var BANK_STAFF_MOBILE_NUMBER ="bank_staff_number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FIRST_NAME TEXT," +
                "$LAST_NAME TEXT," +
                "$MOBILE_NUMBER TEXT," +
                "$GENDER TEXT," +
                "$STATE TEXT," +
                "$COUNTRY TEXT," +
                "$DATE_OF_BIRTH TEXT," +
                "$ADDRESS TEXT," +
                "$POSTAL TEXT," +
                "$EMAIL TEXT," +
                "$EMERGENCY_CONTACT_NAME TEXT," +
                "$MOBILE TEXT," +
                "$RELATIONSHIP TEXT," +
                "$RELATION_WITH_BANK_STAFF TEXT," +
                "$BANK_STAFF_NAME TEXT," +
                "$Is_Any_Relation TEXT," +
                "$BANK_STAFF_MOBILE_NUMBER TEXT)"
        db?.execSQL(createTable)

        Log.d("IS CREATED RELATION_WITH_BANK_STAFF", "==================$RELATION_WITH_BANK_STAFF")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

fun insertData(infoData: InformationFormDataClass): Long {
    val db = writableDatabase
    Log.d("INSERT TAG", "Data insert into database")
    val value = ContentValues().apply {
        put(FIRST_NAME, infoData.first_name)
        put(LAST_NAME, infoData.last_name)
        put(MOBILE_NUMBER, infoData.mobile)
        put(GENDER, infoData.gender)
        put(STATE, infoData.state)
        put(COUNTRY, infoData.country)
        put(DATE_OF_BIRTH, infoData.date_of_birth)
        put(ADDRESS, infoData.address)
        put(POSTAL, infoData.postal)
        put(EMAIL, infoData.email)
    }
    val status =db.insert(TABLE_NAME, null, value)
    db.close()
    return status
}

    @SuppressLint("Recycle", "Range")
    fun getFormData():ArrayList<InformationFormDataClass>{

       val formData = ArrayList<InformationFormDataClass>()

        val db = readableDatabase
        val query ="select * from $TABLE_NAME"
        val cursor =db.rawQuery(query,null)
        Log.d("CURSOR", "================$cursor")
        if (cursor != null){
            if (cursor.moveToNext()){
                Log.d("CURSOR MOVE TO FIRST", "============$cursor")
                do {
                    val insertInfo = InformationFormDataClass(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(FIRST_NAME)) ?: "",
                        cursor.getString(cursor.getColumnIndex(LAST_NAME)) ?: "",
                        cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)) ?: "",
                        cursor.getString(cursor.getColumnIndex(GENDER)) ?: "",
                        cursor.getString(cursor.getColumnIndex(STATE)) ?: "",
                        cursor.getString(cursor.getColumnIndex(COUNTRY)) ?: "",
                        cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH)) ?: "",
                        cursor.getString(cursor.getColumnIndex(ADDRESS)) ?: "",
                        cursor.getString(cursor.getColumnIndex(POSTAL)) ?: "",
                        cursor.getString(cursor.getColumnIndex(EMAIL)) ?: ""
                    )
                    formData.add(insertInfo)
                }while (cursor.moveToNext())
            }
            cursor.close()
        }
        return formData
    }

    fun insertGuardianData(guardianInfo : EmergencyContactDataClass):Long{

        val db = writableDatabase
        Log.d("INSERT TAG", "Data insert into database")
        val value = ContentValues().apply {
            put(EMERGENCY_CONTACT_NAME, guardianInfo.emergency_contact)
            put(MOBILE, guardianInfo.mobile_number)
            put(RELATIONSHIP, guardianInfo.relationship)
            put(RELATION_WITH_BANK_STAFF, guardianInfo.bank_staff_relation)
            Log.d("RELATION_WITH_BANK_STAFF", "============== $RELATION_WITH_BANK_STAFF")
            put(BANK_STAFF_NAME, guardianInfo.bank_staff_name)
            put(Is_Any_Relation, guardianInfo.any_relation)
            put(BANK_STAFF_MOBILE_NUMBER, guardianInfo.bank_staff_number)
        }
        val status =db.insert(TABLE_NAME, null, value)
        db.close()
        return status
    }

}