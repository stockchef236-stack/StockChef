package com.stockchef.app.presentation.addedit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateStockContent(
    state: AddEditUiState,
    onNameChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onSave: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add / Update Ingredient") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onSave) {
                Icon(Icons.Default.Save, contentDescription = "Save")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = state.name,
                onValueChange = onNameChange,
                label = { Text("Ingredient Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.quantity,
                onValueChange = onQuantityChange,
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Image preview will be here (Commit 11)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}