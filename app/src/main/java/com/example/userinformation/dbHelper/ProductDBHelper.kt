package com.example.userinformation.dbHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.userinformation.informationform.model.EmergencyContactDataClass
import com.example.userinformation.informationform.model.YourInformationDataClass
import com.example.userinformation.model.ClothItem
import com.example.userinformation.model.Rating
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProductDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        private const val DATABASE_NAME = "form_details.db"
        private const val VERSION = 18
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
        private var SPINNER_ITEM = "spinner_item"
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
        private var PRODUCT_SIZE = "productSize"
        private var PRODUCT_COUNT = "productCount"
        private var SUB_TOTAL = "subTotal"
        private var DELIVERY_CHARGE = "deliveryCharge"
        private var DISCOUNT = "discount"
        private var TOTAL_COST = "totalCost"
        private var ADD_TO_CART = "addToCart"
        private var USER_ADDRESS = "userAddress"
        private var ORDER_CONFIRM = "orderConfirm"
        private var DELIVERY_DATE = "deliveryDate"
        private var DELIVERY_BY = "deliveryBy"
        private var ORDER_ID = "orderId"
        private var CARD_NUMBER = "atmCartNumber"
        private var CARD_EXPIRY_DATE = "validCartDate"
        private var CARD_HOLDER_NAME = "cardHolderName"
        private var PAYMENT_TYPE = "paymentType"
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
        Log.d("TAG", "onCreate: ==================Info table")

        val createClothTable = "create table $CLOTH_TABLE_NAME(" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$TITLE TEXT," +
                "$PRICE DOUBLE," +
                "$DESCRIPTION TEXT," +
                "$CATEGORY TEXT," +
                "$IMAGE TEXT," +
                "$RATE DOUBLE," +
                "$COUNT INTEGER," +
                "$IS_FAV INTEGER," +
                "$PRODUCT_SIZE TEXT," +
                "$PRODUCT_COUNT INTEGER," +
                "$SUB_TOTAL LONG," +
                "$DELIVERY_CHARGE INTEGER," +
                "$DISCOUNT INTEGER," +
                "$TOTAL_COST LONG," +
                "$ADD_TO_CART INT," +
                "$USER_ADDRESS TEXT," +
                "$ORDER_CONFIRM TEXT," +
                "$DELIVERY_DATE TEXT," +
                "$ORDER_ID LONG," +
                "$DELIVERY_BY TEXT," +
                "$CARD_NUMBER LONG," +
                "$CARD_EXPIRY_DATE TEXT," +
                "$CARD_HOLDER_NAME TEXT," +
                "$PAYMENT_TYPE TEXT" +
                ")"
        db?.execSQL(createClothTable)
        Log.d("TAG", "onCreate: ==================cloth_table")
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

        val db = writableDatabase
        val values = ContentValues().apply {
            put(TITLE, clothItem.title)
            put(PRICE, clothItem.price)
            put(DESCRIPTION, clothItem.description)
            put(CATEGORY, clothItem.category)
            put(IMAGE, clothItem.image)
            put(RATE, clothItem.rating.rate)
            put(COUNT, clothItem.rating.count)
            put(IS_FAV, 0)
            put(PRODUCT_SIZE, clothItem.productSize)
            put(PRODUCT_COUNT, clothItem.productCount)
            put(SUB_TOTAL, clothItem.subTotal)
            put(DELIVERY_CHARGE, clothItem.deliveryCharge)
            put(DISCOUNT, clothItem.discount)
            put(TOTAL_COST, clothItem.totalCost)
            put(ADD_TO_CART, clothItem.addToCart)
            put(USER_ADDRESS, clothItem.userAddress)
            put(ORDER_CONFIRM, clothItem.orderConfirm)
            put(DELIVERY_DATE, clothItem.deliveryDate)
            put(ORDER_ID, clothItem.orderId)
            put(DELIVERY_BY, clothItem.deliveryBy)
            put(CARD_NUMBER, clothItem.cartNumber)
            put(CARD_EXPIRY_DATE, clothItem.cardExpiryDate)
            put(CARD_HOLDER_NAME, clothItem.cardHolderName)
            put(PAYMENT_TYPE, clothItem.paymentType)

        }
        val status = db.insert(CLOTH_TABLE_NAME, null, values).toString()
        db.close()

        return status
    }

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun validateCartFormatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MM-dd", Locale.getDefault())
        return dateFormat.format(date)
    }

    @SuppressLint("Range")
    fun getAllClothItems(): List<ClothItem> {
        val clothItemList = mutableListOf<ClothItem>()

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $CLOTH_TABLE_NAME", null)

        cursor.use { cursor ->
            while (cursor.moveToNext()) {
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

                val productSize =
                    cursor.getString(cursor.getColumnIndex(PRODUCT_SIZE)) ?: "Default Size"
                val productCount = cursor.getInt(cursor.getColumnIndex(PRODUCT_COUNT))
                val subTotal = cursor.getLong(cursor.getColumnIndex(SUB_TOTAL))
                val deliveryCharge = cursor.getInt(cursor.getColumnIndex(DELIVERY_CHARGE))
                val discount = cursor.getInt(cursor.getColumnIndex(DISCOUNT))
                val totalCost = cursor.getLong(cursor.getColumnIndex(TOTAL_COST))
                val addToCart = cursor.getInt(cursor.getColumnIndex(ADD_TO_CART))
                val userAddress = cursor.getString(cursor.getColumnIndex(USER_ADDRESS)) ?: "null"
                val orderConfirm =
                    cursor.getString(cursor.getColumnIndex(ORDER_CONFIRM)) ?: "00-00-00"
                val deliveryDate =
                    cursor.getString(cursor.getColumnIndex(DELIVERY_DATE)) ?: "00-00-00"
                val orderId = cursor.getLong(cursor.getColumnIndex(ORDER_ID))
                val deliveryBy = cursor.getString(cursor.getColumnIndex(DELIVERY_BY)) ?: "null"
                val atmCartNumber = cursor.getLong(cursor.getColumnIndex(CARD_NUMBER))
                val cardExpiryDate =
                    cursor.getString(cursor.getColumnIndex(CARD_EXPIRY_DATE)) ?: "00-00-00"
                val cardHolderName =
                    cursor.getString(cursor.getColumnIndex(CARD_HOLDER_NAME)) ?: "null"
                val paymentType = cursor.getString(cursor.getColumnIndex(PAYMENT_TYPE)) ?: "null"
                Log.d(
                    "TAG",
                    "useraddress -$userAddress------------------orderconfirm ------------$orderConfirm" +
                            "-----deliverydate-----------$deliveryDate------------- orderid----------$orderId--------- deliveryby--------------$deliveryBy" +
                            "---------atmcartnumber-------$atmCartNumber----------------------validcartdate--------_$cardExpiryDate -------------- $cardHolderName"
                )
                val clothItem =
                    ClothItem(
                        id,
                        title,
                        price,
                        description,
                        category,
                        image,
                        rating,
                        isFAV,
                        productSize,
                        productCount,
                        subTotal,
                        deliveryCharge,
                        discount,
                        totalCost,
                        addToCart,
                        userAddress,
                        orderConfirm,
                        deliveryDate,
                        orderId,
                        deliveryBy,
                        atmCartNumber.toString(),
                        cardExpiryDate,
                        cardHolderName,
                        paymentType
                    )
                clothItemList.add(clothItem)
            }
        }
        return clothItemList
    }


    fun updateFavState(itemId: Int, itemIsFav: Int) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(IS_FAV, itemIsFav)
        }
        db.update(
            CLOTH_TABLE_NAME,
            values,
            "$ID = ?",
            arrayOf(itemId.toString())
        )

        db.close()
    }

    fun updateAddToCartStatus(itemId: Int, itemAddToCart: Int) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(ADD_TO_CART, itemAddToCart)
        }
        db.update(
            CLOTH_TABLE_NAME,
            values,
            "$ID = ?",
            arrayOf(itemId.toString())
        )

        db.close()
    }

    fun updateProductSize(itemId: Int, itemSize: String) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(PRODUCT_SIZE, itemSize)
        }
        db.update(
            CLOTH_TABLE_NAME,
            values,
            "$ID = ?",
            arrayOf(itemId.toString())
        )

        db.close()
    }


    @SuppressLint("Range")
    fun getObjectById(itemId: Int): ClothItem? {
        var clothItem: ClothItem? = null

        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $CLOTH_TABLE_NAME  WHERE $ID=?"

        db.rawQuery(selectQuery, arrayOf(itemId.toString())).use { cursor ->
            while (cursor?.moveToNext() == true) {
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
                val productSize =
                    cursor.getString(cursor.getColumnIndex(PRODUCT_SIZE)) ?: "Default Size"
                val productCount = cursor.getInt(cursor.getColumnIndex(PRODUCT_COUNT))
                val subTotal = cursor.getLong(cursor.getColumnIndex(SUB_TOTAL))
                val deliveryCharge = cursor.getInt(cursor.getColumnIndex(DELIVERY_CHARGE))
                val discount = cursor.getInt(cursor.getColumnIndex(DISCOUNT))
                val totalCost = cursor.getLong(cursor.getColumnIndex(TOTAL_COST))

                val addToCart = cursor.getInt(cursor.getColumnIndex(ADD_TO_CART))
                val userAddress = cursor.getString(cursor.getColumnIndex(USER_ADDRESS)) ?: "null"
                val orderConfirm =
                    cursor.getString(cursor.getColumnIndex(ORDER_CONFIRM)) ?: "00-00-00"
                val deliveryDate =
                    cursor.getString(cursor.getColumnIndex(DELIVERY_DATE)) ?: "00-00-00"
                val orderId = cursor.getLong(cursor.getColumnIndex(ORDER_ID))
                val deliveryBy = cursor.getString(cursor.getColumnIndex(DELIVERY_BY)) ?: "null"
                val atmCartNumber = cursor.getString(cursor.getColumnIndex(CARD_NUMBER))
                val cardExpiryDate =
                    cursor.getString(cursor.getColumnIndex(CARD_EXPIRY_DATE)) ?: "00-00-00"
                val cardHolderName =
                    cursor.getString(cursor.getColumnIndex(CARD_HOLDER_NAME)) ?: "null"
                val paymentType = cursor.getString(cursor.getColumnIndex(PAYMENT_TYPE)) ?: "null"

                clothItem =
                    ClothItem(
                        id,
                        title,
                        price,
                        description,
                        category,
                        image,
                        rating,
                        isFAV,
                        productSize,
                        productCount,
                        subTotal,
                        deliveryCharge,
                        discount,
                        totalCost,
                        addToCart,
                        userAddress,
                        orderConfirm,
                        deliveryDate,
                        orderId,
                        deliveryBy,
                        atmCartNumber,
                        cardExpiryDate,
                        cardHolderName,
                        paymentType
                    )

            }
        }
        return clothItem
    }

    fun updateAddToCartCount(itemId: Int, newCount: Int) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(PRODUCT_COUNT, newCount)
        }
        db.update(
            CLOTH_TABLE_NAME,
            values,
            "$ID = ?",
            arrayOf(itemId.toString())
        )

        db.close()
    }

    @SuppressLint("Range")
    fun getProductCount(itemId: Int): Int {
        val db = this.readableDatabase
        val cursor =
            db.query(CLOTH_TABLE_NAME, null, "$ID=?", arrayOf(itemId.toString()), null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val stockCount = cursor.getInt(cursor.getColumnIndex("productCount"))
            cursor.close()
            return stockCount
        }
        return 0
    }

    fun updateStockCount(id: Int, newStockCount: Int) {

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COUNT, newStockCount)
        }

        db.update(CLOTH_TABLE_NAME, values, "$ID = ?", arrayOf(id.toString()))

        db.close()

    }

    @SuppressLint("Range")
    fun getStockCount(itemId: Int): Int {
        val db = this.readableDatabase
        val cursor =
            db.query(CLOTH_TABLE_NAME, null, "$ID=?", arrayOf(itemId.toString()), null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val stockCount = cursor.getInt(cursor.getColumnIndex("count"))
            cursor.close()
            return stockCount
        }
        return 0
    }


    fun updateTotalMRP(totalCost: Long) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(TOTAL_COST, totalCost)
        }
        db.update(CLOTH_TABLE_NAME, contentValues, null, null)
        db.close()
    }

    fun updateCardHolderDetails(
        cardHolderName: String,
        cardNumber: String,
        cardExpiryDate: String
    ) {

        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(CARD_HOLDER_NAME, cardHolderName)
            put(CARD_NUMBER, cardNumber)
            put(CARD_EXPIRY_DATE, cardExpiryDate)
        }

        db.update(CLOTH_TABLE_NAME, contentValues, null, null)
        db.close()
    }

    fun updatePaymentType(paymentType:String){
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(PAYMENT_TYPE, paymentType)
        }

        db.update(CLOTH_TABLE_NAME, contentValues, null, null)
        db.close()
    }
    @SuppressLint("Range")
    fun getTotalPrice(): Double {
        val db = this.readableDatabase
        val selectQuery = "SELECT SUM($TOTAL_COST) FROM $CLOTH_TABLE_NAME WHERE $ADD_TO_CART = 1"
        val cursor = db.rawQuery(selectQuery, null)

        var totalPrice = 0.00

        if (cursor.moveToFirst()) {

            totalPrice = cursor.getDouble(cursor.getColumnIndex(TOTAL_COST))

            cursor.close()

            return totalPrice

        }

        return 0.00
    }
}