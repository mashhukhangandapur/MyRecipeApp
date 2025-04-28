package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme


/**
 * Main activity is the launcher activity of the application.
 * It sets up the compose UI and initializes the navigation controller
 * This activity uses Jetpack Compose and follows the MVVM architecture.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     * It enables edge-to-edge layout and sets the content using Jetpack Compose.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Initializes the NavController for handling navigation between composables
            val navController = rememberNavController()
            // Applies the app theme and sets the main content composable
            MyRecipeAppTheme {
                    RecipeApp(navController = navController)
            }
        }
    }
}

