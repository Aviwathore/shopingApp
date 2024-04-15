package com.example.userinformation.dashboard.productdetails.dbhelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.userinformation.dashboard.productdetails.model.Product

class ProductDB(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "Products.db"
        private var DATABASE_VERSION = 1
        private val TABLE_NAME = "Product"
        private val KEY_ID = "product_id"
        private val KEY_NAME = "product_name"
        private val KEY_CATEGORY = "product_category"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "create table $TABLE_NAME" +
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$KEY_NAME TEXT ," +
                "$KEY_CATEGORY TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun insertProduct(context: Context, name: String, category: String) {

        val dpHelper = ProductDB(context)

        val database = dpHelper.writableDatabase

        val data = ContentValues().apply {
            put(KEY_NAME, name)
            put(KEY_CATEGORY, category)
        }

        database.insert(TABLE_NAME, null, data)

        database.close()
    }

    @SuppressLint("Recycle", "Range")
    fun getAllProducts(context: Context, product: ArrayList<Product>) {
        val dbHelper = ProductDB(context)
        val database = dbHelper.readableDatabase

        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
            val category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY))

            val model = Product(id, name, category)
            product.add(model)
        }

        cursor.close()
        database.close()
    }

    fun deleteProduct(context: Context, productId: Product): Int {

        val dbHelper = ProductDB(context)
        val database = dbHelper.writableDatabase

        val removeProduct =
            database.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(productId.productId.toString()))

        return removeProduct
    }

    fun updateProduct(context: Context, productId: Int, productName: String, productCategory: String): Int {
        val dbHelper = ProductDB(context)
        val database = dbHelper.writableDatabase

        val data =ContentValues().apply {
            put(KEY_NAME, productName)
            put(KEY_CATEGORY, productCategory)
        }

        val updatedProductDetail= database.update(TABLE_NAME, data, "$KEY_ID=?",
            arrayOf(productId.toString())
        )
        database.close()

        return updatedProductDetail
    }

}

