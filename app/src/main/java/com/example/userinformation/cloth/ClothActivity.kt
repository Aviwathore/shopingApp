package com.example.userinformation.cloth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.cloth.api.ClothComment
import com.example.userinformation.cloth.api.CommentInterface
import com.example.userinformation.databinding.ActivityClothBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://jsonplaceholder.typicode.com/"
class Cloth : AppCompatActivity() {
    private lateinit var text: TextView
    var clothCommentArraylist :ArrayList<ClothComment> = ArrayList<ClothComment>()
    private lateinit var binding: ActivityClothBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cloth)

        binding = ActivityClothBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        text= findViewById(R.id.commentList)
        getCommentData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCommentData() {

        // create an object of retroBuilder

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CommentInterface::class.java)

        val retrofitData = retrofitBuilder.getData()


        retrofitData.enqueue(object : Callback<List<ClothComment>?> {
            @SuppressLint("NotifyDataSetChanged", "WrongViewCast")
            override fun onResponse(
                call: Call<List<ClothComment>?>,
                response: Response<List<ClothComment>?>
            ) {
                clothCommentArraylist = (response.body() as ArrayList<ClothComment>?)!!
                val commentList = ArrayList<String>() // List to hold formatted comments

                // Format comments and add them to the list
                for (comment in clothCommentArraylist) {
                    val formattedComment = "ID: ${comment.id}\n" +
                            "Name: ${comment.name}\n" +
                            "Post ID: ${comment.postId}\n"
                    commentList.add(formattedComment)
                }

                val listAdapter = ArrayAdapter<String>(
                    this@Cloth, // activity name
                    R.layout.row_layout, // row layout
                    R.id.listText, // ID of the TextView in row layout
                    commentList
                )

                binding.commentList.isClickable= true

                val listView = findViewById<ListView>(R.id.listComment)
                binding.commentList.setOnClickListener{
                    val builder=AlertDialog.Builder(this@Cloth)
                    builder.setTitle("Welcome To Electronics Shop")
                        .setMessage("Have a good day !!")
                    val alertDialog : AlertDialog =builder.create()
                    alertDialog.show()
                }
                listView.adapter = listAdapter



            }

            override fun onFailure(call: Call<List<ClothComment>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure :" + t.message)
            }
        })

    }

}