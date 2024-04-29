package com.example.userinformation.intent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.userinformation.databinding.ActivityIntentBinding

class IntentActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityIntentBinding
    private val REQUEST_CALL =1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenWebpage.setOnClickListener{

            val intent = Intent( Intent.ACTION_VIEW, Uri.parse("https://kotlinlang.org/docs/extensions.html#declaring-extensions-as-members"))
            startActivity(intent)
        }

        binding.btnOpenCall.setOnClickListener{
           val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: +1234567890")
            startActivity(intent)
        }

        binding.btnOpenCamera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
        binding.btnSendText.setOnClickListener{
            val text = binding.edtIntentText.text.toString()

            val intent = Intent(Intent.ACTION_SEND)
            intent.type= "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,text)

            startActivity(Intent.createChooser(intent, "Share Via"))
        }

        binding.btnCall.setOnClickListener{
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
                }else {
                    call()
                }
        }

    }
    private fun call() {
        val mobileNumber = binding.call.text.toString()
        val intent =Intent(Intent.ACTION_CALL)
        intent.data= Uri.parse("tel:$mobileNumber")
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call()
            }
        }
    }
}