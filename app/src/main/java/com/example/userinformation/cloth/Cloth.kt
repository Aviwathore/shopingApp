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
import com.example.userinformation.cloth.api.Comment
import com.example.userinformation.cloth.api.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://jsonplaceholder.typicode.com/"
class Cloth : AppCompatActivity() {
    private lateinit var text: TextView
    var modelArraylist :ArrayList<Comment> = ArrayList<Comment>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cloth)

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


        retrofitData.enqueue(object : Callback<List<Comment>?> {
            @SuppressLint("NotifyDataSetChanged", "WrongViewCast")
            override fun onResponse(
                call: Call<List<Comment>?>,
                response: Response<List<Comment>?>
            ) {
                modelArraylist = (response.body() as ArrayList<Comment>?)!!
                val commentList = ArrayList<String>() // List to hold formatted comments

                // Format comments and add them to the list
                for (comment in modelArraylist) {
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
                val listView = findViewById<ListView>(R.id.listComment)
                listView.adapter = listAdapter

            }

            override fun onFailure(call: Call<List<Comment>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure :" + t.message)
            }
        })

    }

}