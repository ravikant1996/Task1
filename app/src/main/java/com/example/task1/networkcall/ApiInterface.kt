package com.example.task1.networkcall

import com.example.task1.model.User
import com.example.task1.model.UserItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUsers(): Response<User>

}