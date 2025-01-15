package com.nhatbui.nutrition.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.nhatbui.nutrition.R

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: Any?
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        topLevelRoutes.forEach { topLevelRoute ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            if (topLevelRoute.route == currentRoute) {
                                topLevelRoute.selectedIcon
                            } else {
                                topLevelRoute.unselectedIcon
                            }
                        ),
                        contentDescription = stringResource(topLevelRoute.nameRes),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        text = stringResource(topLevelRoute.nameRes),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (topLevelRoute.route == currentRoute) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors()
                    .copy(selectedIndicatorColor = Color.Transparent),
                selected = currentRoute?.let { it::class == topLevelRoute.route::class } ?: false,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

    }
}

data class TopLevelRoute<T : Any>(
    @StringRes val nameRes: Int,
    val route: T,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)

val topLevelRoutes = listOf(
    TopLevelRoute(
        nameRes = R.string.home_tab_label,
        route = Route.Home,
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home
    ),
    TopLevelRoute(
        nameRes = R.string.streak_tab_label,
        route = Route.Streak,
        selectedIcon = R.drawable.ic_streak_filled,
        unselectedIcon = R.drawable.ic_streak
    )
)
