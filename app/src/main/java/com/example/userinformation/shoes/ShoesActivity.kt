package com.example.userinformation.shoes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.shoes.api.ShoesUserInterface
import com.example.userinformation.shoes.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val BASE_URL = "https://placeholder-url.com/"
class Shoes : AppCompatActivity() {
    val userList = mutableListOf<UserInfo>()
    private lateinit var idEdt: EditText
    private lateinit var nameEdt: EditText
    private lateinit var ageEdt: EditText
    private lateinit var emailEdt: EditText
    private lateinit var cityEdt: EditText
    private lateinit var contactEdt: EditText
    private lateinit var genderEDt: RadioGroup
    private lateinit var courseEdt: Spinner
    private lateinit var buttonEdt: Button
    private lateinit var responseEdt : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shoes)

        idEdt = (findViewById(R.id.use_id) as? EditText)!!
        nameEdt = (findViewById(R.id.name_id) as? EditText)!!
        ageEdt = (findViewById(R.id.age_id) as? EditText)!!
        emailEdt = (findViewById(R.id.email_id) as? EditText)!!
        cityEdt = (findViewById(R.id.city_id) as? EditText)!!
        contactEdt = (findViewById(R.id.contact_id) as? EditText)!!
        genderEDt = (findViewById(R.id.radio_group_id) as? RadioGroup)!!
        courseEdt = (findViewById(R.id.course_id) as? Spinner)!!
//        courseEdt = resources.getStringArray(R.array.Images)
        buttonEdt = (findViewById(R.id.submit_id) as? Button)!!
        responseEdt = (findViewById(R.id.response_id) as? TextView)!!

        // this code for spinner
        val imageArray =resources.getStringArray(R.array.Images)
        val adapter =ArrayAdapter(this,android.R.layout.simple_spinner_item, imageArray)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        courseEdt.adapter= adapter


        buttonEdt.setOnClickListener {

//            val selectorGender = when (genderEDt.checkedRadioButtonId) {
//                R.id.male_id -> "Male"
//                R.id.female_id -> "Female"
//                else -> ' '
//            }
//            createNewUser(
//                idEdt.text.toString(),
//                nameEdt.text.toString(),
//                ageEdt.text.toString(),
//                emailEdt.text.toString(),
//                cityEdt.text.toString(),
//                contactEdt.text.toString(),
//                genderEDt.toString(),
//                courseEdt.selectedItem.toString()
//            )

//            val newUser= UserInfo(
//               id = idEdt.text.toString(),
//                name = nameEdt.text.toString(),
//                age = ageEdt.text.toString(),
//                email = emailEdt.text.toString(),
//                city = cityEdt.text.toString(),
//                contactNum = contactEdt.text.toString(),
//                gender = selectorGender.toString(),
//                course =  courseEdt.toString()
//            )
//            userList.add(newUser)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun createNewUser(
        idd: String,
        namee: String,
        agee: String,
        emaill: String,
        cityy: String,
        contactt: String,
        gendee: String,
        coursee: String
    ) {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ShoesUserInterface::class.java)

        val retrofitData = retrofitBuilder.createPost(idd, namee, agee, emaill, cityy, contactt, gendee, coursee)

        Log.d("aaaaaaaaaaaaaaaaaaaaa", "retrofit created")
        retrofitData.enqueue(object : Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {

                Toast.makeText(this@Shoes, "ADD DATA TO API", Toast.LENGTH_LONG).show()
                val responseApi: UserInfo? = response.body()


//                val responseApi : UserInfo? =response.body()
                val responseData = "Response Code : \n" +
                        "User Id : $idd"

                responseEdt.text=responseData

            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.d("ShoesActivity", "BEZ OF PARS THIS ERROR SHOW" +t.message)
            }

        })
    }


}