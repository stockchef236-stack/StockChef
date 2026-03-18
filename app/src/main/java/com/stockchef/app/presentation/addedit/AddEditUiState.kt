package com.stockchef.app.presentation.addedit

data class AddEditUiState(
    val name: String = "",
    val quantity: String = "",
    val imageUrl: String? = null,
    val imageUri: String? = null,
    val isLoading: Boolean = false
)