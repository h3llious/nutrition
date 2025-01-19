package com.nhatbui.nutrition.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhatbui.foodscan.presentation.ui.navigation.FoodScanGraph
import com.nhatbui.foodscan.presentation.ui.navigation.foodScanGraph
import com.nhatbui.nutrition.ui.home.HomeScreen
import com.nhatbui.nutrition.ui.navigation.Route
import com.nhatbui.streak.presentation.ui.StreakScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen(
                onNavigateToFoodScan = {
                    navHostController.navigate(FoodScanGraph)
                }
            )
        }
        foodScanGraph(navHostController)
        composable<Route.Streak> {
            StreakScreen(
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
