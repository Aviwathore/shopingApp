//package com.example.userinformation.cloth.clothdatabase.dbHelper
//
//import android.annotation.SuppressLint
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.util.Log
//import com.example.userinformation.cloth.clothproducts.model.ClothItem
//import com.example.userinformation.cloth.clothproducts.model.Rating
//
//class FashionDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
//
//    companion object {
//
//        private const val DATABASE_NAME = "fashion.DB"
//        private const val VERSION = 1
//        private const val TABLE_NAME = "cloth_info"
//
//        private var ID = "id"
//        private var TITLE = "title"
//        private var PRICE = "price"
//        private var DESCRIPTION = "description"
//        private var CATEGORY = "category"
//        private var IMAGE = "image"
//        private var RATE = "rate"
//        private var COUNT = "count"
//        private var IS_FAV = "is_fav"
//
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        val createTable = "create table $TABLE_NAME(" +
//                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "$TITLE TEXT," +
//                "$PRICE DOUBLE," +
//                "$DESCRIPTION TEXT," +
//                "$CATEGORY TEXT," +
//                "$IMAGE TEXT," +
//                "$RATE DOUBLE," +
//                "$COUNT INTEGER," +
//                "$IS_FAV BOOLEAN" +
//                ")"
//        db?.execSQL(createTable)
//        Log.d("CREATED TABLE", "==============$createTable========${TABLE_NAME}")
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//
//        if (oldVersion<2){
//            db?.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $IS_FAV $Boolean")
//        }
//
//        db?.execSQL("drop table if exists $TABLE_NAME")
//
//    }
//
//    fun insertClothItem(clothItem: ClothItem):Long {
//        val db = this.writableDatabase
//        Log.d("TAG", "clothItem.title : "+clothItem.title)
//        val values = ContentValues().apply {
//            put(TITLE, clothItem.title)
//            put(PRICE, clothItem.price)
//            put(DESCRIPTION, clothItem.description)
//            put(CATEGORY, clothItem.category)
//            put(IMAGE, clothItem.image)
//            put(RATE, clothItem.rating.rate)
//            put(COUNT, clothItem.rating.count)
//            put(IS_FAV, false)
//        }
//        val status = db.insert(TABLE_NAME, null, values)
//        Log.d("TAG", "insertClothItem: $status")
//        db.close()
//        return status
//    }
//
//    @SuppressLint("Range")
//    fun getAllClothItems(): List<ClothItem> {
//        val clothItemList = mutableListOf<ClothItem>()
//        val selectQuery = "SELECT * FROM $TABLE_NAME"
//        val db = this.readableDatabase
//        val cursor = db.rawQuery(selectQuery, null)
//        if (cursor.moveToFirst()) {
//            do {
//                val id = cursor.getInt(cursor.getColumnIndex(ID))
//                val title = cursor.getString(cursor.getColumnIndex(TITLE))
//                val price = cursor.getDouble(cursor.getColumnIndex(PRICE))
//                val description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
//                val category = cursor.getString(cursor.getColumnIndex(CATEGORY))
//                val image = cursor.getString(cursor.getColumnIndex(IMAGE))
//                val rate = cursor.getDouble(cursor.getColumnIndex(RATE))
//                val count = cursor.getInt(cursor.getColumnIndex(COUNT))
//                val rating = Rating(rate,count)
//                val isFAV = cursor.getInt(cursor.getColumnIndex(IS_FAV))
//                val clothItem = ClothItem(id, title,price, description, category, image,rating,isFAV)
//
//                clothItemList.add(clothItem)
//            } while (cursor.moveToNext())
//        }
//        cursor.close()
//        db.close()
//        return clothItemList
//    }
//
//}