package com.example.userinformation.Groceries

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.Groceries.api.GroceriesInterface
import com.example.userinformation.Groceries.model.GroceriesData
import com.example.userinformation.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://reqres.in/"
class Groceries : AppCompatActivity() {
    private lateinit var nameEdt: EditText
    private lateinit var jobEdt: EditText

    private lateinit var responseA: TextView
    private lateinit var text: TextView
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_groceries)
        nameEdt= (findViewById(R.id.user_id) as? EditText)!!
        jobEdt= (findViewById(R.id.j_id) as? EditText)!!
        text = (findViewById(R.id.text) as? TextView)!!
        button =(findViewById(R.id.btn_send_data) as? Button)!!
        responseA= (findViewById(R.id.response_id) as? TextView)!!

        button.setOnClickListener {
            createToDo(
                nameEdt.text.toString(),
                jobEdt.text.toString()
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun createToDo(name: String, job: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GroceriesInterface::class.java)

        val retrofitData = retrofitBuilder.createPost(name,job)

        retrofitData.enqueue(object : Callback<GroceriesData> {
            override fun onResponse(call: Call<GroceriesData>, response: Response<GroceriesData>) {
                Toast.makeText(this@Groceries, "ADD DATA TO API", Toast.LENGTH_LONG).show()
                val responseApi: GroceriesData? = response.body()

                val responseString =
                    "Response Code:${response.code()}\n UserId: ${responseApi?.name}\n Id: ${responseApi?.job}"
                responseA.text = responseString
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<GroceriesData>, t: Throwable) {
                responseA.text = "Error Found: " + t.message
            }
        })
    }
}