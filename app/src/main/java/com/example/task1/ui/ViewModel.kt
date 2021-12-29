package com.example.task1.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task1.Utill.NetworkUtil
import com.example.task1.model.User
import com.example.task1.repository.DataRepository
import com.example.task1.sealedclass.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(context: Context, private val dataRepository: DataRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUsers(context)
        }
    }

    private val userLiveData = MutableLiveData<Response<User>>()

    val users: LiveData<Response<User>>
        get() = userLiveData

    private suspend fun getUsers(context: Context) {
        if (NetworkUtil.isInternetAvailable(context)) {
            try {
                val result = dataRepository.getUsers()
                if (result.body() != null) {
                    for (i in result.body()!!) {
                        dataRepository.addUser(i)
                    }
                    userLiveData.postValue(Response.Success(result.body()))
                } else {
                    userLiveData.postValue(Response.Failure("api error"))
                }
            } catch (e: Exception) {
                userLiveData.postValue(Response.Failure(e.message.toString()))
            }
        } else {
            val users = dataRepository.getUsersFromRoom()
            val user = User()
            for (i in users) {
                user.add(i)
            }
            userLiveData.postValue(Response.Success(user))
        }
    }

}

