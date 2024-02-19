package com.thusee.imagecard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thusee.imagecard.ui.listing.CategoryListingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        route = Graph.IMAGE_GRAPH,
        startDestination = NavigationScreen.List.route
    ) {
        composable(NavigationScreen.List.route) {
            CategoryListingScreen()
        }

    }
}

sealed class NavigationScreen(val route: String) {
    data object List : NavigationScreen("list")
    data object Details : NavigationScreen("details")
}

object Graph {
    const val IMAGE_GRAPH = "image_graph"
}