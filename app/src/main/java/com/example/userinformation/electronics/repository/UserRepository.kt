package com.example.userinformation.electronics.repository

import androidx.lifecycle.LiveData
import com.example.userinformation.electronics.dao.UserDao
import com.example.userinformation.electronics.model.User


class UserRepository(private val userDao: UserDao)  {

    val allUsers: LiveData<List<User>> = userDao.getAllUser()
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}
