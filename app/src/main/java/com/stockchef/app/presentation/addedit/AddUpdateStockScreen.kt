package com.stockchef.app.presentation.addedit

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddUpdateStockScreen(
    viewModel: AddEditViewModel,
    onBack: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    AddUpdateStockContent(
        state = state,
        onNameChange = viewModel::onNameChange,
        onQuantityChange = viewModel::onQuantityChange,
        onSave = {
            viewModel.saveIngredient {
                onBack()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddUpdateStockPreview() {

    val sampleState = AddEditUiState(
        name = "Tomato",
        quantity = "10",
        imageUrl = null
    )

    AddUpdateStockContent(
        state = sampleState,
        onNameChange = {},
        onQuantityChange = {},
        onSave = {}
    )
}