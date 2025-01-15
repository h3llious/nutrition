package com.nhatbui.nutrition.ui.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Home: Route()

    @Serializable
    data object Streak: Route()
}
