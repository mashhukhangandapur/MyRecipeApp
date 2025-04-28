package com.example.myrecipeapp

/**
 * A sealed class representing different screens in the application.
 * This class is used for navigation between screens.
 *
 * @property route The route string used for navigation.
 */
sealed class Screen(val route : String) {
    /**
     * Represents the RecipeScreen in the app.
     * This screen is used for displaying the list of recipe categories.
     */
    object RecipeScreen : Screen("recipescreen")
    /**
     * Represents the DetailScreen in the app.
     * This screen is used for displaying detailed information about a selected recipe.
     */
    object DetailScreen : Screen("detailscreen")
}