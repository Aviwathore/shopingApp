package com.example.userinformation.electronics.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userinformation.electronics.dao.UserDao
import com.example.userinformation.electronics.model.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "userdb"
                ).fallbackToDestructiveMigration()
                    .build() // Add fallbackToDestructiveMigration() here
                INSTANCE = instance
                instance // Return instance here
            }
        }
    }
}
