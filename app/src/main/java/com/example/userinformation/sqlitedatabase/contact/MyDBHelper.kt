package com.example.userinformation.sqlitedatabase.contact

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{
        private const val DATABASE_NAME ="contacts.db"
        private const val DATABASE_VERSION = 1
        private const val  TABLE_NAME ="Contact"
        private const val KEY_ID = "contact_id"
        private const val  KEY_NAME ="contact_name"
        private const val KEY_CONTACT_NUMBER = "contact_number"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // This is a two way to create a database

//        db.execSQL("create table "+ TABLE_NAME +
//        "("+ KEY_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_NAME+ "TEXT," + KEY_CONTACT_NUMBER+"TEXT" +")");

        val createTable = "create table $TABLE_NAME"+
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$KEY_NAME TEXT ,"+
                "$KEY_CONTACT_NUMBER TEXT)"
        db?.execSQL(createTable)


    }


    /*

    // open database
    val dbHelper = MyDBHelper(context)   // instance of your SQLiteOpenHelper subclass
    val database1= dbHelper.readableDatabase   // OR
    val database = dbHelper.writableDatabase

    // close database
    database1.close()

     */

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // if upgrade then drop the existing table and create new table

        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun addContact(context: Context, name : String, contact_num : String){

        val dbHelper = MyDBHelper(context)  // initialize SQLiteOpenHelper
        val database = dbHelper.writableDatabase  // instance of writable or open database

        val values = ContentValues().apply {
            put(KEY_NAME, name)
            put(KEY_CONTACT_NUMBER, contact_num)
        }
        database.insert(TABLE_NAME, null, values)

        // close database
        database.close()

    }

    @SuppressLint("Range")
    fun getContacts(context: Context, contact:ArrayList<ContactModel>){

        val dbHelper = MyDBHelper(context)
        val database = dbHelper.readableDatabase

        val query ="select * from $TABLE_NAME"
        val cursor =database.rawQuery(query, null)

        val list =ArrayList<ContactModel>()
        while (cursor.moveToNext()){

            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
            val contact_num = cursor.getString(cursor.getColumnIndex(KEY_CONTACT_NUMBER))

            val model = ContactModel(id, name, contact_num)
            contact.add(model)
        }
        cursor.close()
        database.close()
    }

    fun updateContact(context: Context, contactModel: ContactModel) : Int{
        val dbHelper = MyDBHelper(context)

        val database = dbHelper.writableDatabase

        val modal = ContentValues()
        modal.put(KEY_CONTACT_NUMBER, contactModel.contactNum)

        val updatedContact = database.update(TABLE_NAME, modal, "$KEY_ID=?", arrayOf(contactModel.id.toString()))

        database.close()

        return  updatedContact
    }

    fun deleteContact(context: Context, contactId:Int) :Int{
        val dpHelper = MyDBHelper(context)
        val database = dpHelper.writableDatabase

        val deletedContacts = database.delete(TABLE_NAME, "$KEY_ID=?",
            arrayOf(contactId.toString())
        )

        return  deletedContacts
    }
}