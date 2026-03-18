package com.stockchef.app.domain.repository

import android.content.Context
import com.stockchef.app.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {

    suspend fun getIngredients(): Flow<List<Ingredient>>

    suspend fun getIngredientById(id: String): Ingredient?

    suspend fun addIngredient(ingredient: Ingredient)

    suspend fun updateIngredient(ingredient: Ingredient)

    suspend fun deleteIngredient(id: String)

    suspend fun uploadImage(context: Context, uri: String): String
}