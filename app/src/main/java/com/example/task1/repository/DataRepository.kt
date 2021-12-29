package com.example.task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task1.model.UserItem
import com.example.task1.networkcall.ApiInterface
import com.example.task1.roomdb.UserDatabase
import com.example.task1.sealedclass.Response

class DataRepository(
    private val apiInterface: ApiInterface,
    private val userDatabase: UserDatabase,
) {

    suspend fun getUsers() = apiInterface.getUsers()

    suspend fun getUsersFromRoom() = userDatabase.userDao().getUsers()

    suspend fun addUser(body: UserItem) = userDatabase.userDao().addUser(body)

}