package com.example.userinformation.dashboard

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.userinformation.Groceries.GroceriesActivity
import com.example.userinformation.R
import com.example.userinformation.cloth.Cloth
import com.example.userinformation.customViewForRecycleView.CARVActivity
import com.example.userinformation.dashboard.productdetails.ViewProductsActivity
import com.example.userinformation.databinding.ActivityMainBinding
import com.example.userinformation.fragmentToActivity.FragmentToActivity
import com.example.userinformation.home.Home
import com.example.userinformation.informationform.InformationFormActivity
import com.example.userinformation.intent.IntentActivity
import com.example.userinformation.pharmacy.Pharmacy
import com.example.userinformation.userdetails.LoginActivity
import com.google.android.material.appbar.MaterialToolbar

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var topAppBar: MaterialToolbar

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var adapter: ImageViewAdapter
    private lateinit var doLayout: LinearLayout

    companion object {
        val REQUEST_CODE_SPEECH_INPUT = 1
    }
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageContainer()

//        val gradientColors = listOf(
//            R.color.pink,
//            R.color.blue,
//            R.color.green
//        )
        val gradientColors = listOf(
            ContextCompat.getColor(this, R.color.pink),
            ContextCompat.getColor(this, R.color.white),
            ContextCompat.getColor(this, R.color.seek_bar_background)
        )
        val gradientAngles = listOf(
            GradientDrawable.Orientation.BL_TR,
            GradientDrawable.Orientation.BR_TL,
            GradientDrawable.Orientation.TOP_BOTTOM
        )

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

//
//                val backgroundColor = gradientColors[position % gradientColors.size]
//                viewPager2.setBackgroundColor(ContextCompat.getColor(this@DashBoardActivity, backgroundColor))

                val gradientAngle = gradientAngles[position % gradientAngles.size]

                val gradientDrawable = GradientDrawable(gradientAngle, gradientColors.toIntArray())
                gradientDrawable.cornerRadius = resources.getDimension(R.dimen.card_corner_radius)

                viewPager2.background = gradientDrawable

                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 4000)
                adapter.updateDotIndicator(position)
            }
        })


        topAppBar = findViewById(R.id.app_bar)
        val mic = findViewById<ActionMenuItemView>(R.id.microphone)

//        val search = findViewById<ActionMenuItemView>(R.id.search)

        val logOut = findViewById<ActionMenuItemView>(R.id.logout)
        logOut.setOnClickListener{
            logOutUser()
        }

        mic.setOnClickListener {
            speechRecognizer()

        }
//
//        topAppBar.setOnMenuItemClickListener() {
//            showMenu()
//        }
    }


//    override fun onOptionsItemSelected(item: MenuItem):Boolean{
//
//        return when(item.itemId){
//            R.id.cloth -> showd()
//            else ->super.onOptionsItemSelected(item)
//        }
//    }

    private fun showd(): Boolean {
        startActivity(Intent(this, IntentActivity::class.java))
        return true
    }

    private fun logOutUser() {
        Toast.makeText(this, "Search Click !", Toast.LENGTH_SHORT).show()
        val editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit()
        editor.putBoolean("flag", false)
        editor.apply()

        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun speechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "mr-IN")
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US, hi-IN, mr-IN")
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
        intent.putExtra(
            RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,
            1000
        )
        startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val speechResult = result?.get(0)
                if (!speechResult.isNullOrEmpty()) {
                    showMessage(speechResult)
                }
            }
        }
    }

    private fun showMessage(message: String) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(message)
            .setTitle("Speech Recognition Result")
            .setPositiveButton("OKAY") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }

    private fun imageContainer() {
        viewPager2 = binding.viewPager

        handler = Handler(Looper.myLooper()!!)
        doLayout = binding.dotLayout

        val listImage = listOf(
            R.drawable.money,
            R.drawable.specialoffer,
            R.drawable.cloth
        )

        val title = listOf(
            "ACTIVATE FINANCE AMOUNT",
            "TODAY SPECIAL OFFER",
            "CLOTH STORE"
        )
        val des = listOf(
            "Minimum INR5000 financing amount is required",
            "Festive season approaching means irresistible discounts and perfect gifts.",
            "Stylish clothing for both men and women."
        )
        adapter = ImageViewAdapter(listImage, title, des, viewPager2, doLayout)
        viewPager2.adapter = adapter

        for (i in listImage.indices) {
            val dot = ImageView(this)
            dot.setImageResource(if (i == 0) R.drawable.selected_dot else R.drawable.unselected_dot)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0) // Adjust margins as needed
            doLayout.addView(dot, params)
        }
        viewPager2.offscreenPageLimit = 1
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    fun onCloth(view: View) {
        if (view.id == R.id.btnCloths) {
            startActivity(Intent(this, Cloth::class.java))
        }
    }

    fun onForm(view: View){
        if (view.id==R.id.btnInfoForm){
            startActivity(Intent(this, InformationFormActivity::class.java))
        }
    }
    fun onHome(view: View) {
        if (view.id == R.id.btn_home) {
            startActivity(Intent(this, Home::class.java))
        }
    }

//    fun onGuardian(view: View) {
//        if (view.id == R.id.btn_guardian) {
//            startActivity(Intent(this, EmergencyContactFormActivity::class.java))
//        }
//    }

    fun onPharmacy(view: View) {
        if (view.id == R.id.btn_pharmacy) {
            startActivity(Intent(this, Pharmacy::class.java))
        }
    }

    fun onGroceries(view: View) {
        if (view.id == R.id.btn_groceries) {
            startActivity(Intent(this, GroceriesActivity::class.java))
        }
    }

//    fun onPickMe(view: View) {
//        if (view.id == R.id.amazon) {
//            startActivity(Intent(this, TimePickerActivity::class.java))
//
//        }
//    }


    fun onNotes(view: View) {
        if (view.id == R.id.customAdaptor) {
            startActivity(Intent(this, ViewProductsActivity::class.java))
        }
    }

    fun onProduct(view: View) {
        if (view.id == R.id.products) {
            startActivity(Intent(this, IntentActivity::class.java))
        }
    }

    fun onCARV(view: View) {
        if (view.id == R.id.carv) {
            startActivity(Intent(this, CARVActivity::class.java))
        }
    }

//    fun onUserInfo(view: View) {
//        if (view.id == R.id.user_info) {
//            startActivity(Intent(this, LifeCycleOFActivity::class.java))
//        }
//    }

    fun onFragmentToActivity(view: View) {
        if (view.id == R.id.fragment) {
            startActivity(Intent(this, FragmentToActivity::class.java))
        }
    }
//    fun onCustomDialog(view: View) {
//        if (view.id == R.id.custom_dialog) {
//            startActivity(Intent(this, CustomDialogBoxActivity::class.java))
//        }
//    }


}