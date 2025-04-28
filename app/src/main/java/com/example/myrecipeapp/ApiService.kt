package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Retrofit instance configured for connecting to the MealDB API.
 * It includes a base URL and a converter for JSON to object mapping using Gson.
 */
private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/") // The base URL for the MealDB API.
    .addConverterFactory(GsonConverterFactory.create()) // Adds GsonConverterFactory to handle JSON conversion
    .build()

/**
 * Service instance used to make API calls.
 * This interface defines the endpoint to retrieve categories from the MealDB API.
 */
val recipeService = retrofit.create(ApiService::class.java)
/**
 * ApiService interface that defines the API endpoints.
 * The @GET annotation specifies the endpoint for retrieving meal categories.
 */
interface ApiService {
    /**
     * Makes a GET request to fetch categories of meals.
     * The response will be mapped to a [CategoriesResponse] object.
     *
     * @return The [CategoriesResponse] object containing the categories data.
     */
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}
