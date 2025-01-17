package com.nhatbui.foodscan.presentation.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.nhatbui.foodscan.presentation.ui.result.ScanResultScreen
import com.nhatbui.foodscan.presentation.ui.scanner.FoodScanScreen

fun NavGraphBuilder.foodScanGraph(navController: NavController) {
    navigation<FoodScanGraph>(startDestination = FoodScanRoute.Scanner) {
        composable<FoodScanRoute.Scanner> {
            FoodScanScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToResult = { uri ->
                    navController.navigate(FoodScanRoute.Result(uri)) {
                        popUpTo(FoodScanRoute.Scanner) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<FoodScanRoute.Result> { entry ->
            val route = entry.toRoute<FoodScanRoute.Result>()
            ScanResultScreen(route.uri)
        }
    }
}
