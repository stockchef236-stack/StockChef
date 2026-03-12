package com.stockchef.app.data.repository

import com.stockchef.app.domain.model.Ingredient
import com.stockchef.app.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IngredientRepositoryImpl : IngredientRepository {

    override suspend fun getIngredients(): Flow<List<Ingredient>> = flow {
        emit(emptyList())
    }

    override suspend fun getIngredientById(id: String): Ingredient? {
        return null
    }

    override suspend fun addIngredient(ingredient: Ingredient) {
    }

    override suspend fun updateIngredient(ingredient: Ingredient) {
    }

    override suspend fun deleteIngredient(id: String) {
    }
}