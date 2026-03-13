package com.stockchef.app.data.remote.mapper

import com.stockchef.app.data.remote.dto.SupabaseIngredientDto
import com.stockchef.app.domain.model.Ingredient

fun SupabaseIngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        id = id,
        name = name,
        quantity = quantity,
        imageUrl = image_url
    )
}

fun Ingredient.toDto(): SupabaseIngredientDto {
    return SupabaseIngredientDto(
        id = id,
        name = name,
        quantity = quantity,
        image_url = imageUrl
    )
}