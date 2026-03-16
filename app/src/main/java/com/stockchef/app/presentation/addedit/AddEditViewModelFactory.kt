package com.stockchef.app.presentation.addedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stockchef.app.data.repository.IngredientRepositoryImpl
import com.stockchef.app.domain.repository.IngredientRepository

class AddEditViewModelFactory(
    private val ingredientId: String?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddEditViewModel::class.java)) {

            val repository: IngredientRepository = IngredientRepositoryImpl()

            @Suppress("UNCHECKED_CAST")
            return AddEditViewModel(repository, ingredientId) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}