package com.nhatbui.nutrition.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nhatbui.foodscan.presentation.ui.navigation.FoodScanRoute
import com.nhatbui.nutrition.ui.navigation.BottomNavigationBar
import com.nhatbui.nutrition.ui.navigation.Route
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()

    val bottomBarOwners = persistentListOf(
        Route.Home,
        Route.Streak,
        FoodScanRoute.Result("")
    )
    val currentBottomNavRoute = remember(currentNavBackStackEntry) {
        bottomBarOwners.firstOrNull { owner ->
            currentNavBackStackEntry?.destination?.hierarchy?.any { navDestination ->
                navDestination.hasRoute(owner::class)
            } ?: false
        }
    }
    val showBottomBar =
        bottomBarOwners.any { route ->
            currentBottomNavRoute?.let { it::class == route::class } ?: false
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentBottomNavRoute
                )
            }
        },
        contentWindowInsets = WindowInsets.systemBars.exclude(WindowInsets.statusBars)
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            AppNavHost(navController)
        }
    }
}
