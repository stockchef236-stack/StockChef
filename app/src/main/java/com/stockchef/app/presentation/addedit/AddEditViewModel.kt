package com.stockchef.app.presentation.addedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockchef.app.domain.model.Ingredient
import com.stockchef.app.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val repository: IngredientRepository,
    private val ingredientId: String? = null
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditUiState())
    val uiState: StateFlow<AddEditUiState> = _uiState.asStateFlow()

    init {
        ingredientId?.let { loadIngredient(it) }
    }

    private fun loadIngredient(id: String) {
        viewModelScope.launch {
            val ingredient = repository.getIngredientById(id)
            ingredient?.let {
                _uiState.value = _uiState.value.copy(
                    name = it.name,
                    quantity = it.quantity.toString(),
                    imageUrl = it.imageUrl
                )
            }
        }
    }

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value)
    }

    fun onQuantityChange(value: String) {
        _uiState.value = _uiState.value.copy(quantity = value)
    }

    fun saveIngredient(onSuccess: () -> Unit) {
        viewModelScope.launch {

            val qty = _uiState.value.quantity.toIntOrNull() ?: 0

            val ingredient = Ingredient(
                id = ingredientId ?: "",
                name = _uiState.value.name,
                quantity = qty,
                imageUrl = _uiState.value.imageUrl
            )

            if (ingredientId == null) {
                repository.addIngredient(ingredient)
            } else {
                repository.updateIngredient(ingredient)
            }

            onSuccess()
        }
    }

    fun onImageCaptured(uri: String) {
        _uiState.value = _uiState.value.copy(imageUri = uri)
    }
}