package com.example.userinformation.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivitySplashScreenBinding
import com.example.userinformation.userdetails.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // show this screen until load all data

        startHeavyTask()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startHeavyTask() {

        LongOperation().execute()
    }

    // If we inheritance inner class by AsyncTask : it's run on background using that application is not hang   // parameters :- param, progress, result

    @SuppressLint("StaticFieldLeak")
    private open inner class LongOperation : AsyncTask<String?, Void?, String?>() {
        override fun doInBackground(vararg params: String?): String? {
            for (i in 0..3){
                try {
                    Thread.sleep(1000)
                }catch (e:Exception){
                    Thread.interrupted()
                }
            }
            return "result"
        }

        override fun onPostExecute(result: String?) {

            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
        }
    }

}