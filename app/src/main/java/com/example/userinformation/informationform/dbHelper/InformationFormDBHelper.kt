package com.example.userinformation.informationform.dbHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.userinformation.cloth.clothproducts.model.ClothItem
import com.example.userinformation.cloth.clothproducts.model.Rating

class InformationFormDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        private const val DATABASE_NAME = "form_details.db"
        private const val VERSION = 10
        private const val TABLE_NAME = "information"
        private var FIRST_NAME = "first_name"
        private var LAST_NAME = "last_name"
        private var EMAIL = "email"
        private var MOBILE = "mobile_number"
        private var DATE_OF_BIRTH = "date_of_birth"
        private var ADDRESS = "address"
        private var AADHAAR_NUMBER = "aadhaar_number"
        private var GENDER = "gender"
        private var STATE = "state"
        private var POSTAL = "postal"
        private var COUNTRY = "country"
        private var SPINNER_ITEM= "spinner_item"
        private var EMERGENCY_CONTACT = "emergency_contact"
        private var CONTACT_NUMBER = "contact_number"
        private var RELATIONSHIP = "relationship"
        private var ANY_RELATIONSHIP = "any_relationship"
        private var BANK_STAFF_NAME = "bank_staff_name"
        private var RELATION_WITH_BANK_STAFF = "relation_with_bank_staff"
        private var BANK_STAFF_MOBILE_NUMBER = "bank_staff_mobile_number"

        // Second Table

        private const val CLOTH_TABLE_NAME = "cloth_info"
        private var ID = "id"
        private var TITLE = "title"
        private var PRICE = "price"
        private var DESCRIPTION = "description"
        private var CATEGORY = "category"
        private var IMAGE = "image"
        private var RATE = "rate"
        private var COUNT = "count"
        private var IS_FAV = "is_fav"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table $TABLE_NAME(" +
                "$FIRST_NAME TEXT," +
                "$LAST_NAME TEXT," +
                "$EMAIL TEXT," +
                "$MOBILE TEXT," +
                "$DATE_OF_BIRTH TEXT," +
                "$ADDRESS TEXT," +
                "$AADHAAR_NUMBER TEXT," +
                "$GENDER TEXT," +
                "$STATE TEXT," +
                "$POSTAL TEXT," +
                "$COUNTRY TEXT," +
                "$SPINNER_ITEM TEXT," +
                "$EMERGENCY_CONTACT TEXT," +
                "$CONTACT_NUMBER TEXT," +
                "$RELATIONSHIP TEXT," +
                "$ANY_RELATIONSHIP TEXT," +
                "$BANK_STAFF_NAME TEXT," +
                "$RELATION_WITH_BANK_STAFF TEXT," +
                "$BANK_STAFF_MOBILE_NUMBER TEXT)"
        db?.execSQL(createTable)
        Log.d("CREATED TABLE", "==============$createTable========$TABLE_NAME")

        val createClothTable = "create table $CLOTH_TABLE_NAME(" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$TITLE TEXT," +
                "$PRICE DOUBLE," +
                "$DESCRIPTION TEXT," +
                "$CATEGORY TEXT," +
                "$IMAGE TEXT," +
                "$RATE DOUBLE," +
                "$COUNT INTEGER," +
                "$IS_FAV INTEGER" +
                ")"
        db?.execSQL(createClothTable)
        Log.d("CREATED CLOTH TABLE", "==============$createClothTable========$CLOTH_TABLE_NAME")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if (oldVersion < VERSION) {
            db?.execSQL("drop table if exists $TABLE_NAME")
            db?.execSQL("drop table if exists $CLOTH_TABLE_NAME")
            onCreate(db)
        }
    }

    fun insertData(data: YourInformationDataClass): Long {

        val db = writableDatabase

        val value = ContentValues().apply {
            put(FIRST_NAME, data.firstName)
            put(LAST_NAME, data.lastName)
            put(EMAIL, data.email)
            put(MOBILE, data.mobileNumber)
            put(DATE_OF_BIRTH, data.dateOfBirth)
            put(ADDRESS, data.address)
            put(AADHAAR_NUMBER, data.aadhaarNumber)
            put(GENDER, data.gender)
            put(STATE, data.state)
            put(POSTAL, data.postal)
            put(COUNTRY, data.country)
            put(SPINNER_ITEM, data.spinnerData)
        }
        val status = db.insert(TABLE_NAME, null, value)
        db.close()
        return status
    }

    fun insertGuardianData(data: EmergencyContactDataClass): Long {

        val db = writableDatabase

        val value = ContentValues().apply {
            put(EMERGENCY_CONTACT, data.emergencyContact)
            put(CONTACT_NUMBER, data.contactNumber)
            put(RELATIONSHIP, data.relationship)
            put(ANY_RELATIONSHIP, data.anyRelationship)
            put(BANK_STAFF_NAME, data.bankStaffName)
            put(RELATION_WITH_BANK_STAFF, data.relationWithBankStaff)
            put(BANK_STAFF_MOBILE_NUMBER, data.bankStaffMobileNumber)
        }
        val status = db.insert(TABLE_NAME, null, value)
        db.close()
        return status
    }

    fun getTableSize(): Long {
        val db = readableDatabase
        var cursor: Cursor? = null

        var size: Long = 0
        cursor = db.rawQuery("select * from information", null)
        if (cursor.moveToFirst()) {
            size = cursor.getLong(0)
        }
        Log.i("Number of Records", " :: " + cursor.count);
        cursor.close()
        db.close()
        return size
    }

    @SuppressLint("Range")
    fun getAllEmergencyContacts(): List<EmergencyContactDataClass> {
        val db = readableDatabase
        val contacts = mutableListOf<EmergencyContactDataClass>()

        val columns = arrayOf(
            EMERGENCY_CONTACT,
            CONTACT_NUMBER,
            RELATIONSHIP,
            ANY_RELATIONSHIP,
            BANK_STAFF_NAME,
            RELATION_WITH_BANK_STAFF,
            BANK_STAFF_MOBILE_NUMBER
        )

        val cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        cursor.use {
            while (it.moveToNext()) {
                val emergencyContactIndex = it.getColumnIndex(EMERGENCY_CONTACT)
                val emergencyContact =
                    if (emergencyContactIndex != -1) it.getString(emergencyContactIndex) else null

                val contactNumberIndex = it.getColumnIndex(CONTACT_NUMBER)
                val contactNumber =
                    if (contactNumberIndex != -1) it.getString(contactNumberIndex) else null

                val relationshipIndex = it.getColumnIndex(RELATIONSHIP)
                val relationship =
                    if (relationshipIndex != -1) it.getString(relationshipIndex) else null

                val anyRelationshipIndex = it.getColumnIndex(ANY_RELATIONSHIP)
                val anyRelationship =
                    if (anyRelationshipIndex != -1) it.getString(anyRelationshipIndex) else null

                val bankStaffNameIndex = it.getColumnIndex(BANK_STAFF_NAME)
                val bankStaffName =
                    if (bankStaffNameIndex != -1) it.getString(bankStaffNameIndex) else null
                val relationWithBankStaffIndex =
                    it.getColumnIndex(RELATION_WITH_BANK_STAFF)

                val relationWithBankStaff =
                    if (relationWithBankStaffIndex != -1) it.getString(relationWithBankStaffIndex) else null

                val bankStaffMobileNumberIndex = it.getColumnIndex(BANK_STAFF_MOBILE_NUMBER)
                val bankStaffMobileNumber =
                    if (bankStaffMobileNumberIndex != -1) it.getString(bankStaffMobileNumberIndex) else null

                val contact = EmergencyContactDataClass(
                    emergencyContact.toString() ?: "",
                    contactNumber.toString() ?: "",
                    relationship.toString() ?: "",
                    anyRelationship.toString() ?: "",
                    bankStaffName.toString() ?: "",
                    relationWithBankStaff.toString() ?: "",
                    bankStaffMobileNumber.toString() ?: ""

                )
                contacts.add(contact)
            }
        }

        db.close()
        return contacts
    }

    fun getAllYourInfoFormDetails(): List<YourInformationDataClass> {

        val db = readableDatabase
        val infoList = mutableListOf<YourInformationDataClass>()

        val cursor = db.rawQuery("select * from $TABLE_NAME", null)

        cursor.use {
            while (cursor.moveToNext()) {
                val firstIndex = it.getColumnIndex(FIRST_NAME)
                val firstName = if (firstIndex != -1) it.getString(firstIndex) else null

                val lastIndex = it.getColumnIndex(LAST_NAME)
                val lastName = if (lastIndex != -1) it.getString(lastIndex) else null

                val emailIndex = it.getColumnIndex(EMAIL)
                val email = if (emailIndex != -1) it.getString(emailIndex) else null

                val mobileIndex = it.getColumnIndex(MOBILE)
                val mobile = if (mobileIndex != -1) it.getString(mobileIndex) else null

                val dateOfBirthIndex = it.getColumnIndex(DATE_OF_BIRTH)
                val dateOfBirth =
                    if (dateOfBirthIndex != -1) it.getString(dateOfBirthIndex) else null

                val addressIndex = it.getColumnIndex(ADDRESS)
                val address = if (addressIndex != -1) it.getString(addressIndex) else null

                val aadhaarNumberIndex = it.getColumnIndex(AADHAAR_NUMBER)
                val aadhaar =
                    if (aadhaarNumberIndex != -1) it.getString(aadhaarNumberIndex) else null

                val genderIndex = it.getColumnIndex(GENDER)
                val gender = if (genderIndex != -1) it.getString(genderIndex) else null

                val stateIndex = it.getColumnIndex(STATE)
                val state = if (stateIndex != -1) it.getString(stateIndex) else null

                val postalIndex = it.getColumnIndex(POSTAL)
                val postal = if (postalIndex != -1) it.getString(postalIndex) else null

                val countryIndex = it.getColumnIndex(COUNTRY)
                val country = if (countryIndex != -1) it.getString(countryIndex) else null

                val spinnerIndex = it.getColumnIndex(SPINNER_ITEM)
                val spinner = if (spinnerIndex != -1) it.getString(spinnerIndex) else null
                Log.d("SPINNER", "=====================$spinner")

                val infoListItem = YourInformationDataClass(

                    firstName.toString() ?: "",
                    lastName.toString() ?: "",
                    email.toString() ?: "",
                    mobile.toString() ?: "",
                    dateOfBirth.toString() ?: "",
                    address.toString() ?: "",
                    aadhaar.toString() ?: "",
                    gender.toString() ?: "",
                    state.toString() ?: "",
                    postal.toString() ?: "",
                    country.toString() ?: "",
                    spinner.toString() ?: ""
                )
                infoList.add(infoListItem)
            }
        }
        db.close()
        return infoList
    }

    fun insertClothItem(clothItem: ClothItem): String {

        Log.d("TAG", "insertClothItem:-------------------------- ")
        val db = this.writableDatabase
        Log.d("TAG", "clothItem.title : " + clothItem.title)
        val values = ContentValues().apply {
            put(TITLE, clothItem.title)
            put(PRICE, clothItem.price)
            put(DESCRIPTION, clothItem.description)
            put(CATEGORY, clothItem.category)
            put(IMAGE, clothItem.image)
            put(RATE, clothItem.rating.rate)
            put(COUNT, clothItem.rating.count)
            put(IS_FAV, 0)
        }
        val status = db.insert(CLOTH_TABLE_NAME, null, values).toString()
        Log.d("TAG", "insertClothItem: $status")
        db.close()

        return status
    }

    @SuppressLint("Range")
    fun getAllClothItems(): List<ClothItem> {
        Log.d("TAG", "getAllClothItems:----------------------------- ")
        val clothItemList = mutableListOf<ClothItem>()

        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $CLOTH_TABLE_NAME"

        db.rawQuery(selectQuery, null).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(ID))
                    val title = cursor.getString(cursor.getColumnIndex(TITLE))
                    val price = cursor.getDouble(cursor.getColumnIndex(PRICE))
                    val description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
                    val category = cursor.getString(cursor.getColumnIndex(CATEGORY))
                    val image = cursor.getString(cursor.getColumnIndex(IMAGE))
                    val rate = cursor.getDouble(cursor.getColumnIndex(RATE))
                    val count = cursor.getInt(cursor.getColumnIndex(COUNT))
                    val rating = Rating(rate, count)
                    val isFAV = cursor.getInt(cursor.getColumnIndex(IS_FAV))
                    Log.d("TAG", "isFAV           : " + isFAV)
                    val clothItem =
                        ClothItem(id, title, price, description, category, image, rating, isFAV)
                    Log.d("TAG", "getAllClothItems: " + clothItem.is_fav)
                    clothItemList.add(clothItem)
                } while (cursor.moveToNext())
            }
        }

        db.close()
        return clothItemList
    }

    fun updateFavState(item: ClothItem) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(IS_FAV, item.is_fav)
        }
        val affectedRows = db.update(
            CLOTH_TABLE_NAME,
            values,
            "$ID = ?",
            arrayOf(item.id.toString())
        )

        db.close()
    }

}