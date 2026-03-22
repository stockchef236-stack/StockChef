package com.stockchef.app.presentation.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockchef.app.data.local.IngredientDatabase
import com.stockchef.app.data.remote.SupabaseManager
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val context: Context
) : ViewModel() {

    private val db = IngredientDatabase.getDatabase(context)

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            SupabaseManager.client.auth.signOut()
            onSuccess()
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            db.ingredientDao().clearAll()
        }
    }
}