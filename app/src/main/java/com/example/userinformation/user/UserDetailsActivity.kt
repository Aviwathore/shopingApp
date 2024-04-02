package com.example.userinformation.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityUserDetailsBinding
import com.example.userinformation.user.api.UserApi
import com.example.userinformation.user.modal.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://reqres.in/"
class UserDetails : AppCompatActivity() {

    private  lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSubmit.setOnClickListener{

            val name= binding.usernameId.text.toString()
            val job = binding.jobId.text.toString()

            if (name.isNotEmpty() && job.isNotEmpty()){
                postData(name,job)
            }else{
                Toast.makeText(this,"Please check the field!", Toast.LENGTH_LONG).show()
            }


        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun postData(name: String, job: String) {

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(UserApi::class.java)

        // passing data from our text field to modal class
        val useModal :User = User(name,job)

        // call a method to create a post and passing our modal class
        val call : Call<User?>?=retrofitBuilder.postData(useModal)

        Log.d("Create", "User Created")
        call!!.enqueue(object :Callback<User?>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<User?>, response: Response<User?>) {

                val res =response.body()

                if (res!=null){

//                    Log.d("Success", "Response Pass")
//                    var resp = binding.userResponse.text.toString()
//
//                    val responseString ="Response Code: ${response.code()}\n" +
//                            "Name : $name\n" +
//                            "Job :$job"
//                    resp=responseString

                    binding.userResponse.text="Response Code :${response.code()}\n" +
                            "User Name :$name\n" +
                            "User Role: $job"
                }else{
                    Log.d("Not Response", "Not Response Pass!!")
                }

            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.d("UserActivity", "Response Fail !!"+t.message)
            }

        })
    }
}