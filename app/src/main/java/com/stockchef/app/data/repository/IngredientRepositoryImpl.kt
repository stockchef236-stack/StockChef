package com.stockchef.app.data.repository

import android.content.Context
import android.net.Uri
import com.stockchef.app.data.remote.SupabaseManager
import com.stockchef.app.data.remote.dto.SupabaseIngredientDto
import com.stockchef.app.data.remote.mapper.toDto
import com.stockchef.app.data.remote.mapper.toIngredient
import com.stockchef.app.domain.model.Ingredient
import com.stockchef.app.domain.repository.IngredientRepository
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json.Default.decodeFromString
import java.io.File

class IngredientRepositoryImpl() : IngredientRepository {

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

        val dto = InsertIngredientDto(
            name = ingredient.name,
            quantity = ingredient.quantity,
            image_url = ingredient.imageUrl
        )

        SupabaseManager.client
            .from("ingredients")
            .insert(dto)
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

    override suspend fun uploadImage(
        context: Context,
        uri: String
    ): String {

        val inputStream = context.contentResolver
            .openInputStream(Uri.parse(uri))
            ?: throw Exception("Cannot open image")

        val bytes = inputStream.readBytes()

        val fileName = "ingredient_${System.currentTimeMillis()}.jpg"

        SupabaseManager.client.storage
            .from("ingredients")
            .upload(fileName, bytes)

        return SupabaseManager.client.storage
            .from("ingredients")
            .publicUrl(fileName)
    }

    override suspend fun getIngredientsOnce(): List<Ingredient> {

        val response = SupabaseManager.client
            .from("ingredients")
            .select()

        val dtoList =
            decodeFromString<List<SupabaseIngredientDto>>(response.data)

        return dtoList.map { it.toIngredient() }
    }
}

@Serializable
data class InsertIngredientDto(
    val name: String,
    val quantity: Int,
    val image_url: String? = null
)