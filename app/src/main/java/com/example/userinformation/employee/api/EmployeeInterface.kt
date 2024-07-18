package com.example.userinformation.employee.api

import com.example.userinformation.employee.model.Employee
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EmployeeInterface {
    @FormUrlEncoded
    @POST("/create")
    fun createEmployee(
        @Field("employee_name") employee_name: String,
        @Field("employee_salary") employee_salary: String,
        @Field("employee_age") employee_age: String,
    ) :Call<Employee>
}
