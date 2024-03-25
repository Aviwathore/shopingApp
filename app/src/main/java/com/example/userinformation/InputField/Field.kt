package com.example.userinformation.InputField

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityFieldBinding

class Field : AppCompatActivity() {
    private lateinit var binding: ActivityFieldBinding

    var result = ArrayList<CheckBox>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // checkbox code
            binding.idjava.setOnCheckedChangeListener{buttonView, isChecked ->

                if (isChecked){
                    result.add(binding.idjava)
//                    Log.d("JAVA", "ischecked")
                }
            }
            binding.idkotlin.setOnCheckedChangeListener{buttonView, isChecked ->
                if (isChecked){
                    result.add(binding.idkotlin)
//                    Log.d("KOTLIN", "ischecked")
                }
            }
            binding.idphp.setOnCheckedChangeListener{ buttonView, isChecked ->
                if (isChecked){
                    result.add(binding.idphp)
//                    Log.d("PHP", "ischecked")
                }

            }
        binding.idcheck.setOnClickListener{
//            Log.d("selected Item"," This items are selected")
            for (item in result){
                binding.idselectedCourse.append("\t"+item.tag.toString()+"\t")
            }

        }

        // chips code

        binding.idbook.setOnClickListener{
            Log.d("Book"," Book Chip Selected")

        }
        binding.idpen.setOnClickListener{
            Log.d("PEN", "Pen Chip Selected")
        }

        binding.NoteBook.setOnClickListener{
            Log.d("NOTEBOOK","Note book chip selected")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}