package com.example.userinformation.pharmacy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.pharmacy.api.PharmacyInterface
import com.example.userinformation.pharmacy.model.PharmacyProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://jsonplaceholder.typicode.com/"
class Pharmacy : AppCompatActivity() {

    private lateinit var userIdEdt: EditText
    private lateinit var idEdt: EditText
    private lateinit var titleEdt: EditText
    private lateinit var completedEdt: EditText

    private lateinit var responseA: TextView
    private lateinit var text: TextView
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pharmacy)

        userIdEdt = (findViewById(R.id.userId_id) as?EditText)!!
//        idEdt = findViewById(R.id.id) as? EditText as EditText  this is also one syntax
        idEdt = (findViewById(R.id.id_id) as? EditText)!!
        titleEdt = (findViewById(R.id.title_id) as? EditText) !!
        completedEdt = (findViewById(R.id.completed_id) as? EditText)!!

        text = (findViewById(R.id.text) as? TextView)!!
        button =(findViewById(R.id.btn_send_data) as? Button)!!
        responseA= (findViewById(R.id.response_id) as? TextView)!!

//        postData(name.text.toString(), price.text.toString())
//        CreateToDo(
//            userIdEdt.text.toString(),
//            idEdt.text.toString(),
//            titleEdt.toString(),
//            completedEdt.text.toString()
//        )

        button.setOnClickListener {
            // Call the CreateToDo function with the text from EditText fields
            createToDo(
                userIdEdt.text.toString(),
                idEdt.text.toString(),
                titleEdt.text.toString(),
                completedEdt.text.toString()
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

private fun createToDo(userId: String, id: String, title: String, completed: String) {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(PharmacyInterface::class.java)

    val retrofitData = retrofitBuilder.createPost(userId, id, title, completed)

    retrofitData.enqueue(object : Callback<PharmacyProduct> {
        override fun onResponse(call: Call<PharmacyProduct>, response: Response<PharmacyProduct>) {
            Toast.makeText(this@Pharmacy, "ADD DATA TO API", Toast.LENGTH_LONG).show()
            val responseApi: PharmacyProduct? = response.body()

            val responseString =
                "Response Code:${response.code()}\n UserId: ${responseApi?.userId}\n Id: ${responseApi?.id}\n Title: ${responseApi?.title}\n Completed: ${responseApi?.completed}"

//            val responseString= "yes done"
            responseA.text = responseString
        }

        @SuppressLint("SetTextI18n")
        override fun onFailure(call: Call<PharmacyProduct>, t: Throwable) {
            responseA.text = "Error Found: " + t.message
        }
    })
}

}

