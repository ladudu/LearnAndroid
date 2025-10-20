package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private  val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = categoriesState.value.copy(
                    recipes = response.categories,
                    isLoading = false,
                    error = null
                )

            }catch (e: Exception){
                _categoriesState.value = categoriesState.value.copy(
                    error = "Error fetching categories ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    data class RecipeState(
        val isLoading: Boolean = false,
        val recipes: List<Category> = emptyList(),
        val error: String? = null
    )
}