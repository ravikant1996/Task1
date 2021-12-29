package com.example.task1.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.task1.model.UserItem

@Database(entities = [UserItem::class], version = 1)
@TypeConverters(Convertors::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, UserDatabase::class.java,
                        "userDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }


}