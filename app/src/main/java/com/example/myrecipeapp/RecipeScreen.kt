package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

/**
 * RecipeScreen displays the main screen of the app.
 * It observes the ViewModel state and shows a loading indicator,
 * error message, or the list of recipe categories accordingly.
 *
 * @param modifier Modifier for UI customization (default is Modifier)
 * @param viewState UI state containing loading, error, and list of categories
 * @param navigateToDetails lambda to handle navigation on category click
 */
@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewState: MainViewModel.RecipeState,
                 navigateToDetails:(Category)-> Unit  ){
    val recipeViewModel: MainViewModel= viewModel()
    val viewState by recipeViewModel.categoriesState
    Box(modifier = Modifier.fillMaxSize()){
        when{
            // Show loading indicator while data is being fetched
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            // Show error message if any error occurs
            viewState.error != null ->{
                Text("ERROR OCURRED")
            }
            // Show list of recipe categories
            else -> {
                //Display Categories
                CategoryScreen(categories = viewState.list, navigateToDetails)

            }
        }
    }
}
/**
 * Displays a grid of categories using LazyVerticalGrid.
 *
 * @param categories List of recipe categories to display
 * @param navigateToDetails lambda function to handle click on a category
 */
@Composable
fun CategoryScreen( categories : List<Category>,
                    navigateToDetails:(Category)-> Unit){
    LazyVerticalGrid(GridCells.Fixed(2), modifier= Modifier.fillMaxSize()) {
        items(categories) {
            category ->
            CategoryItem(category = category, navigateToDetails)
        }
    }
    }

/**
 * Displays a single category item including its image and name.
 * Navigates to detail screen on click.
 * @param category The Category object containing image and title
 * @param navigateToDetails Callback to navigate to the category's details
 */
@Composable
fun CategoryItem(category : Category,
                 navigateToDetails:(Category)-> Unit ){
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable(){  navigateToDetails(category)  },
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                modifier = Modifier.fillMaxSize().aspectRatio(1f),
                contentDescription = null

            )
            Text( text = category.strCategory,
                  color = Color.Black,
                  style = TextStyle(fontWeight = FontWeight.Bold),
                  modifier = Modifier.padding(top = 4.dp)
            )
    }
}