package com.example.task1

import android.app.Application
import com.example.task1.networkcall.ApiInterface
import com.example.task1.networkcall.RetrofitHelper
import com.example.task1.repository.DataRepository
import com.example.task1.roomdb.UserDatabase

class UserApplication : Application() {
    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val database = UserDatabase.getDatabase(applicationContext)

        dataRepository = DataRepository(apiInterface, database)

    }
}