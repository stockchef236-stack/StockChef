package com.stockchef.app.presentation.addedit

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddUpdateStockScreen(
    viewModel: AddEditViewModel,
    onBack: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    AddUpdateStockContent(
        state = state,
        onNameChange = viewModel::onNameChange,
        onQuantityChange = viewModel::onQuantityChange,
        onSave = {
            viewModel.saveIngredient(context) {
                onBack()
            }
        },
        onImageCaptured = viewModel::onImageCaptured
    )
}

@Preview(showBackground = true)
@Composable
fun AddUpdateStockPreview() {

    val sampleState = AddEditUiState(
        name = "Tomato",
        quantity = "10",
        imageUrl = null,
        imageUri = null
    )

    AddUpdateStockContent(
        state = sampleState,
        onNameChange = {},
        onQuantityChange = {},
        onSave = {},
        onImageCaptured = {}
    )
}