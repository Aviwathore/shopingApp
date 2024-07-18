package com.example.userinformation.electronics.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.userinformation.electronics.model.User
@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User)

    @Query("select * from User_info")
    fun getAllUser(): LiveData<List<User>>
}
