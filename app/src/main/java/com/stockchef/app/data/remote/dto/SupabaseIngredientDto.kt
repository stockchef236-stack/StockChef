package com.stockchef.app.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SupabaseIngredientDto(
    val id: String,
    val name: String,
    val quantity: Int,
    val image_url: String? = null
)