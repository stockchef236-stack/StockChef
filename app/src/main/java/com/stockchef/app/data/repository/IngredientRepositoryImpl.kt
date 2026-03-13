package com.stockchef.app.data.repository

import com.stockchef.app.data.remote.SupabaseManager
import com.stockchef.app.data.remote.dto.SupabaseIngredientDto
import com.stockchef.app.data.remote.mapper.toDto
import com.stockchef.app.data.remote.mapper.toIngredient
import com.stockchef.app.domain.model.Ingredient
import com.stockchef.app.domain.repository.IngredientRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json.Default.decodeFromString

class IngredientRepositoryImpl : IngredientRepository {

    override suspend fun getIngredients(): Flow<List<Ingredient>> = flow {

        val response = SupabaseManager.client
            .from("ingredients")
            .select()

        val dtoList =
            decodeFromString<List<SupabaseIngredientDto>>(response.data)

        emit(dtoList.map { it.toIngredient() })
    }

    override suspend fun getIngredientById(id: String): Ingredient? {

        val response = SupabaseManager.client
            .from("ingredients")
            .select {
                filter {
                    eq("id", id)
                }
            }

        val dtoList =
            decodeFromString<List<SupabaseIngredientDto>>(response.data)

        return dtoList.firstOrNull()?.toIngredient()
    }

    override suspend fun addIngredient(ingredient: Ingredient) {

        SupabaseManager.client
            .from("ingredients")
            .insert(ingredient.toDto())
    }

    override suspend fun updateIngredient(ingredient: Ingredient) {

        SupabaseManager.client
            .from("ingredients")
            .update(ingredient.toDto()) {
                filter {
                    eq("id", ingredient.id)
                }
            }
    }

    override suspend fun deleteIngredient(id: String) {

        SupabaseManager.client
            .from("ingredients")
            .delete {
                filter {
                    eq("id", id)
                }
            }
    }
}