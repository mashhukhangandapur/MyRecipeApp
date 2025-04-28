package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

/**
 * Displays the detailed screen for a selected recipe category.
 * Shows the category name, image, and description.
 *
 * @param category The Category object passed from the previous screen.
 */
@Composable
fun CategoryDetailsScreen(category: Category){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the category title
        Text(text=category.strCategory, modifier = Modifier.padding(top = 20.dp), textAlign = TextAlign.Center)

        // Display the category image
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "${category.strCategory} Thumbnail",
            modifier = Modifier.wrapContentSize().aspectRatio(1f)
        )
        // Display the full category description in a scrollable column
        Text(text= category.strCategoryDescription,
            textAlign = TextAlign.Justify,
            modifier = Modifier.verticalScroll(rememberScrollState())
            )

    }
}