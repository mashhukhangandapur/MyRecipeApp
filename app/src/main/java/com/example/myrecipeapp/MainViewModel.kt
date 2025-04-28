package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the UI-related data and business logic for the main screen.
 * It handles fetching categories from the API and updating the UI state accordingly.
 */
class MainViewModel : ViewModel() {
    /**
     * Private state holding the current state of the recipe categories.
     */
        private val _categoryState = mutableStateOf(RecipeState())
    /**
     * Public immutable state that represents the recipe categories' UI state.
     */
        val categoriesState : State<RecipeState> = _categoryState

    /**
     * Initializes the ViewModel by calling the `fetchCategories` function to fetch the recipe categories
     * as soon as the ViewModel is created.
     */
    init{
        fetchCategories()
    }
    /**
     * Fetches the categories from the API asynchronously.
     * It updates the state based on the result of the API request.
     */
    private fun fetchCategories() {
        viewModelScope.launch {
            // Launches a coroutine in the ViewModel's scope to fetch categories
            try {
                // Attempting to fetch categories from the API
                val response = recipeService.getCategories()
                // Updates the state with the fetched categories and changes loading state
                _categoryState.value  = _categoryState.value.copy(
                    list = response.categories,// Updates the list of categories
                    loading = false, // Stops the loading spinner
                    error = null // Clears any existing errors

                )
            } catch (e: Exception) {
                // If an error occurs during the API request, update the state with the error message
                _categoryState.value = _categoryState.value.copy(
                    loading = false, // Stops the loading spinner
                    error  = "Error fetching categories ${e.message}" // Sets the error message
                )
            }
        }
    }




/**
 * Data class representing the state of the recipe categories.
 *
 * @property loading A Boolean indicating whether the categories are still being fetched.
 * @property list A list of [Category] objects representing the fetched categories.
 * @property error A nullable string containing any error message encountered during fetching.
 */
    data class RecipeState(
        val loading : Boolean = true,
        val list : List<Category> = emptyList(),
        val error: String ? = null
    )
}