package com.stockchef.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey val id: String,
    val name: String,
    val quantity: Int,
    val imageUrl: String?
)