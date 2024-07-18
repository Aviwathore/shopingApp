package com.example.userinformation.intent

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.userinformation.R
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.ActivityIntentBinding
import com.example.userinformation.intent.colorpicker.ColorPickerFragment
import com.example.userinformation.intent.converttexttospeech.TextToSpeechFragment

class IntentActivity : AppCompatActivity(), View.OnClickListener {

    private  lateinit var binding: ActivityIntentBinding
    private val REQUEST_CALL =1

    private lateinit var seekBarText: TextView
    private lateinit var seekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCall.setOnClickListener(this)
        binding.btnSendText.setOnClickListener(this)
        binding.btnOpenCamera.setOnClickListener(this)
        binding.btnOpenWebpage.setOnClickListener(this)
        binding.btnOpenCall.setOnClickListener(this)
        binding.btnTxtToSpeech.setOnClickListener(this)
        binding.btnColorPicker.setOnClickListener(this)
        binding.btnApiSpinner.setOnClickListener(this)


        selectSeekBar()
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_open_webpage -> openWebPage()
            R.id.btn_open_call -> dialCall()
            R.id.btn_open_camera -> openCamera()
            R.id.btn_send_text -> sendData()
            R.id.btn_call -> callToPerson()
            R.id.btn_txt_to_speech -> convertToSpeech()
            R.id.btn_color_picker -> selectColor()
            R.id.btn_api_spinner ->callApi()

            else -> {}
        }
    }

    private fun callApi() {
//        startActivity(Intent(this, HousingOptionActivity::class.java))
        val fragment = BFragment()

//       supportFragmentManager.beginTransaction().add(fragment,"new").commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_api, fragment).commit()
    }

    private fun selectSeekBar() {

        Log.d("SELECT PROGRESS BAR", "==================")
        seekBarText = binding.txtSeekBar
        seekBar = binding.seekBar

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("onProgressChanged", "=======================progress changes")
                if (progress < 100) {
                    Log.d("Progress", "=======================progress if block")
                    seekBarText.text = "Progress : $progress"
                }
                if (progress == 100) {
                    Log.d("START ACTIVITY", "=======================progress changes")
                    startActivity(
                        Intent(
                            applicationContext,
                            DashBoardActivity::class.java
                        )
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("onStopTrackingTouch", "=======================stop progress")
                if (seekBar != null) {
                    Log.d("onStopTrackingTouch", "======================= seek bar not null")
                    if (seekBar.progress < 100) {
                        Log.d("onStopTrackingTouch", "======================= seek bar less than")
                        Toast.makeText(applicationContext, "Try Again", Toast.LENGTH_SHORT).show()
                        seekBar.progress = 0
                    }
                }
            }


        })

    }


    private fun selectColor() {
        val colorFragment = ColorPickerFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_color_picker, colorFragment)
            .commit()

    }

    private fun convertToSpeech() {

        val fragment = TextToSpeechFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    private fun callToPerson() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL
            )
        } else {
            call()
        }
    }

    private fun sendData() {
        val text = binding.edtIntentText.text.toString()

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)

        startActivity(Intent.createChooser(intent, "Share Via"))
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun dialCall() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel: +1234567890")
        startActivity(intent)
    }

    private fun openWebPage() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://kotlinlang.org/docs/extensions.html#declaring-extensions-as-members")
        )
        startActivity(intent)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val colorPickerFragment = supportFragmentManager.findFragmentById(R.id.fragment_color_picker)
        if (fragment is TextToSpeechFragment) {
            (fragment as TextToSpeechFragment).closeFragment()
        }else if (colorPickerFragment is ColorPickerFragment){
            (colorPickerFragment as ColorPickerFragment).closeFragment()
        }
        else {
            super.onBackPressed()
        }
    }



}