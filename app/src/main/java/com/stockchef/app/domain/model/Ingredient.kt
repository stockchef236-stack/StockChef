package com.stockchef.app.domain.model

data class Ingredient(
    val id: String,
    val name: String,
    val quantity: Int,
    val imageUrl: String?,
    val lowStockThreshold: Int = 5
) {
    val isLowStock: Boolean
        get() = quantity <= lowStockThreshold
}