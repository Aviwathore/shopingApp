package com.example.userinformation.customAdapter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.customAdapter.customAdapter.IconCustomAdapter
import com.example.userinformation.customAdapter.modal.Icons
import com.example.userinformation.customAdapter.singlePage.NewCustomListView
import com.example.userinformation.databinding.ActivityCustomListViewBinding


class CustomListView : AppCompatActivity() {

    private lateinit var binding: ActivityCustomListViewBinding
    private  lateinit var iconList :ArrayList<Icons>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCustomListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intArrayOf(
            R.drawable.pc, R.drawable.cloth, R.drawable.beauty, R.drawable.amazon_logo, R.drawable.groceries, R.drawable.makeup, R.drawable.pharmacy
        )

        val name = arrayOf(
            "1st","2nd","3rd","4th", "5th","6th","7th"
        )

        val lastMessage = arrayOf(
            "gm", "hey", "byy", "gn", "ok", "hello", "hmm"
        )

        iconList= ArrayList()

        for (i in name.indices) {

            val icon =Icons(name[i], image[i], lastMessage[i])
            iconList.add(icon)
        }

        binding.customListView.isClickable=true
        binding.customListView.adapter = IconCustomAdapter(this, iconList)

        binding.customListView.setOnItemClickListener { parent, view, position, id ->

            val name =name[position]
            val imageId=image[position]
            val lastMessage = lastMessage[position]

            val data = Intent(this, NewCustomListView::class.java)
            data.putExtra("name", name)
            data.putExtra("image", imageId)
            data.putExtra("lastmessage", lastMessage)
            startActivity(data)
        }




    }

}