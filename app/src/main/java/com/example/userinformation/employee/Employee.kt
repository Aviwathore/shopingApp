package com.example.userinformation.employee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityEmployeeBinding
import com.example.userinformation.employee.api.EmployeeInterface
import com.example.userinformation.employee.model.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://dummy.restapiexample.com/"
class Employee : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeBinding


    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEmp.setOnClickListener{
            val name = binding.empName.text.toString()
            val salary = binding.empSalary.text.toString()
            val age = binding.empAge.text.toString()
            val st1 = binding.spinnerId.selectedItemId.toString()
            val st =resources.getStringArray(R.array.Status)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, st)
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)



            if(name.isNotEmpty() && salary.isNotEmpty() && age.isNotEmpty() && st.isNotEmpty()){
                createEmployee(name, salary, age)
            }else{
                Toast.makeText(this@Employee, "Please check code", Toast.LENGTH_LONG).show()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("SetTextI18n")
    private fun createEmployee(name: String, salary: String, age: String) {

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(EmployeeInterface::class.java)

        val employeeData =retrofitBuilder.createEmployee(name,salary,age)
        Log.d("Employee", "Employee Created")

        binding.empResponse.text="Emp Name :$name\n" +
                "Emp Salary: $salary\n" +
                "Emp Age: $age"
        employeeData.enqueue(object :Callback<Employee>{
            override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
//                if (response.isSuccessful){
//
//                    val employeeResponse = response.body()
//
//                    binding.empResponse.text=response.code().toString()
//                }else{
//                    Log.d("Activityyyyy","response not succeed")
//                }
            }

            override fun onFailure(call: Call<Employee>, t: Throwable) {
                Log.d("EmployeeActivity", "OnFail"+t.message)
            }

        })
    }


}


