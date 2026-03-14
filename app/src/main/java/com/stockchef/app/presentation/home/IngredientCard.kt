package com.stockchef.app.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stockchef.app.domain.model.Ingredient

@Composable
fun IngredientCard(
    ingredient: Ingredient,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = ingredient.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Qty: ${ingredient.quantity}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (ingredient.isLowStock) {
                AssistChip(
                    onClick = {},
                    label = { Text("Low") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                )
            }
        }
    }
}