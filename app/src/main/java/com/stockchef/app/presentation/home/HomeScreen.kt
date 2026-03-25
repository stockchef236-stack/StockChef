package com.stockchef.app.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stockchef.app.domain.model.Ingredient
import com.stockchef.app.ui.theme.StockChefTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddClick: () -> Unit,
    onItemClick: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadIngredients()
    }

    HomeContent(
        state = state,
        ingredients = viewModel.getFilteredIngredients(),
        onSearchChange = viewModel::onSearchQueryChange,
        onAddClick = onAddClick,
        onItemClick = onItemClick,
        onSettingsClick = onSettingsClick
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    val sampleIngredients = listOf(
        Ingredient(
            id = "1",
            name = "Tomatoes",
            quantity = 10,
            imageUrl = null
        ),
        Ingredient(
            id = "2",
            name = "Onions",
            quantity = 2,
            imageUrl = null
        ),
        Ingredient(
            id = "3",
            name = "Cheese",
            quantity = 15,
            imageUrl = null
        )
    )

    val sampleState = HomeUiState(
        isLoading = false,
        ingredients = sampleIngredients,
        searchQuery = ""
    )

    StockChefTheme {
        HomeContent(
            state = sampleState,
            ingredients = sampleIngredients,
            onSearchChange = {},
            onAddClick = {},
            onItemClick = {},
            onSettingsClick = {}
        )
    }
}