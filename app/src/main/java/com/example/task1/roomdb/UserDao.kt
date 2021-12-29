package com.example.task1.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task1.model.User
import com.example.task1.model.UserItem
import retrofit2.Response

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(users: UserItem)

    @Query("Select * from users")
    suspend fun getUsers(): List<UserItem>
}