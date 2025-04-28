package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * RecipeApp sets up the navigation graph for the application using Jetpack Navigation Compose.
 * It initializes the MainViewModel, observes the UI state, and defines the navigation routes
 * between RecipeScreen and CategoryDetailsScreen.
 *
 * @param navController the NavHostController used to navigate between screens
 */
@Composable
fun RecipeApp(navController: NavHostController){
    // ViewModel responsible for managing UI-related data and business logic
    val recipeViewModel : MainViewModel = viewModel()
    // Observing the state exposed by the ViewModel (categories list, loading, errors, etc.
    val viewState by recipeViewModel.categoriesState

    // Setting up the navigation graph
    NavHost(navController= navController, startDestination = Screen.RecipeScreen.route  ) {
        /**
         * RecipeScreen composable
         * Displays a list of recipe categories.
         * On click of a category, it saves the selected item in the savedStateHandle
         * and navigates to the details screen.
         */
        composable(route= Screen.RecipeScreen.route) {
            RecipeScreen(viewState=viewState, navigateToDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            /**
             * CategoryDetailsScreen composable
             * Retrieves the selected category from the savedStateHandle and shows its details.
             * If no data is found (unlikely), a default empty category is passed.
             */
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat") ?: Category("","","","")
            CategoryDetailsScreen(category = category)
        }
    }
}