package com.thusee.imagecard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thusee.imagecard.ui.listing.CategoryListingScreen
import com.thusee.imagecard.ui.listing.SharedViewModel
import com.thusee.imagecard.ui.listing.details.DetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        route = Graph.IMAGE_GRAPH,
        startDestination = NavigationScreen.List.route
    ) {
        composable(NavigationScreen.List.route) {
            CategoryListingScreen(navController = navController, viewModel = sharedViewModel)
        }
        composable(NavigationScreen.Details.route) {
            DetailsScreen(navController = navController, sharedViewModel = sharedViewModel)
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