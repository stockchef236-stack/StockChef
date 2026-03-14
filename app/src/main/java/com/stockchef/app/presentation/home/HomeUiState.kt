package com.stockchef.app.presentation.home

import com.stockchef.app.domain.model.Ingredient

data class HomeUiState(
    val isLoading: Boolean = false,
    val ingredients: List<Ingredient> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)