package com.stockchef.app.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockchef.app.data.local.IngredientDatabase
import com.stockchef.app.data.local.toDomain
import com.stockchef.app.data.local.toEntity
import com.stockchef.app.domain.model.Ingredient
import com.stockchef.app.domain.repository.IngredientRepository
import com.stockchef.app.utils.NetworkMonitor
import com.stockchef.app.utils.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val context: Context,
    private val repository: IngredientRepository
) : ViewModel() {

    private val db = IngredientDatabase.getDatabase(context)
    private val dao = db.ingredientDao()
    private val networkMonitor = NetworkMonitor(context)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        observeNetwork()
        loadIngredients()
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { connected ->
                if (connected) {
                    loadIngredients()
                }
            }
        }
    }

    fun loadIngredients() {
        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {

                val localData = dao.getAll()

                if (localData.isNotEmpty()) {
                    val mapped = localData.map { it.toDomain() }

                    _uiState.update {
                        it.copy(
                            ingredients = mapped,
                            isLoading = false
                        )
                    }

                    checkLowStock(mapped)
                }

                try {
                    val remoteData = repository.getIngredientsOnce()

                    dao.clearAll()
                    dao.insertAll(remoteData.map { it.toEntity() })

                    _uiState.update {
                        it.copy(
                            ingredients = remoteData,
                            isLoading = false
                        )
                    }

                    checkLowStock(remoteData)

                } catch (e: Exception) {
                    if (localData.isEmpty()) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = "No internet connection"
                            )
                        }
                    }
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    private fun checkLowStock(list: List<Ingredient>) {
        list.forEach { ingredient ->
            if (ingredient.isLowStock) {
                NotificationHelper.showLowStockNotification(
                    context,
                    ingredient.name
                )
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun getFilteredIngredients(): List<Ingredient> {
        val state = _uiState.value

        return if (state.searchQuery.isBlank()) {
            state.ingredients
        } else {
            state.ingredients.filter {
                it.name.contains(state.searchQuery, true)
            }
        }
    }
}