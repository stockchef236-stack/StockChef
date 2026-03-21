package com.stockchef.app.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stockchef.app.data.repository.IngredientRepositoryImpl

class HomeViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {

            val repository = IngredientRepositoryImpl()

            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(context, repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}