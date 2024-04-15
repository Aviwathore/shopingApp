package com.example.userinformation.electronics.repository

import androidx.lifecycle.LiveData
import com.example.userinformation.electronics.dao.UserDao
import com.example.userinformation.electronics.model.User
import java.io.Closeable

class UserRepository(private val userDao: UserDao)  {

    val getAllUser : LiveData<List<User>> = userDao.getAllUser()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}