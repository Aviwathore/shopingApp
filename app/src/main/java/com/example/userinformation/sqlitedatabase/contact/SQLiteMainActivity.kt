package com.example.userinformation.sqlitedatabase.contact

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.databinding.ActivitySqliteMainBinding

class SQLiteMainActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySqliteMainBinding
    private lateinit var    dbHelper :MyDBHelper
    private lateinit var model: ContactModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySqliteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*
        // create object of MyDBHelper class

        dbHelper = MyDBHelper(this)
        dbHelper.addContact(this,"AVi", "4567890234")
        dbHelper.addContact(this,"mahi", "787890234")
        dbHelper.addContact(this,"swati", "907890234")
        dbHelper.addContact(this,"shital", "45667890234")

         */


        // create arraylist for store fetch data

        dbHelper = MyDBHelper(this)

        val list = ArrayList<ContactModel>()
        dbHelper.getContacts(this, list)

        // getContacts(context, ArrayList)
        for (contact in list){
            Log.d("Contact","ID: ${contact.id}, Name : ${contact.name}, Contact_Number : ${contact.contactNum}")
        }

        // update

        model = ContactModel(id = 1, name = "AVi", contactNum = "4567890234")
        dbHelper = MyDBHelper(this)
        val result = dbHelper.updateContact(this, model)

        if (result>0){
            Log.d("SQLiteMainActivity", "Contact Update Successfully")
        }else{
            Log.d("SQLiteMainActivity", "Failed to update contact")
        }

        // delete

        val deletedId= 2
        dbHelper = MyDBHelper(this)
        val deletedContact = dbHelper.deleteContact(this, deletedId)

        if (deletedContact>0){
            Log.d("SQLiteMainActivity", "Contact delete successfully")
        }else{
            Log.d("SQLiteMainActivity", "Failed to  delete contact")
        }
    }
}