package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a single Recipe Category.
 * @property idCategory Unique ID for the category
 * @property strCategory Name of the category (e.g., "Seafood", "Dessert")
 * @property strCategoryThumb URL of the thumbnail image for the category
 * @property strCategoryDescription Detailed description of the category
 */
@Parcelize
data class Category(val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
): Parcelable  // Enables the object to be passed safely between screens via Navigation

/**
 * Response wrapper for the API call that returns a list of categories.
 * The JSON response is expected to have a 'categories' field which contains an array of Category objects.
 */
data class CategoriesResponse(
    val categories: List<Category>
)
