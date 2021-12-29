package com.example.task1.sealedclass

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Failure<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}