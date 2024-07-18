package com.example.userinformation.activityLifeCycle

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.activityLifeCycle.customdialogbox.MyFragmentClass

class LifeCycleOFActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        Toast.makeText(this, "I am onCreate", Toast.LENGTH_LONG).show()
        Log.d("TAG"," RUN ")

        val sm = supportFragmentManager.beginTransaction()
        val myFragment = MyFragmentClass()

        myFragment.show(sm, "Custom Dialog Box Fragment")

    }


    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"I AM onResume",Toast.LENGTH_SHORT).show()
        Log.d("TAG"," RUN ")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this,"I am onPause", Toast.LENGTH_SHORT).show()
        Log.d("TAG"," RUN ")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "I am onStop", Toast.LENGTH_SHORT).show()
        Log.d("TAG"," RUN ")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"I am onRestart", Toast.LENGTH_SHORT).show()
        Log.d("TAG"," RUN ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "I am onDestroy", Toast.LENGTH_LONG).show()
        Log.d("TAG"," RUN ")
    }
}