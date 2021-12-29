package com.example.task1.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task1.repository.DataRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val context: Context, private val dataRepository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModel(context, dataRepository) as T
    }

}