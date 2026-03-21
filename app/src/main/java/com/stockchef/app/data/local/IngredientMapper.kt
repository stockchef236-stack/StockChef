package com.stockchef.app.data.local

import com.stockchef.app.domain.model.Ingredient

fun IngredientEntity.toDomain(): Ingredient {
    return Ingredient(
        id = id,
        name = name,
        quantity = quantity,
        imageUrl = imageUrl
    )
}

fun Ingredient.toEntity(): IngredientEntity {
    return IngredientEntity(
        id = id,
        name = name,
        quantity = quantity,
        imageUrl = imageUrl
    )
}