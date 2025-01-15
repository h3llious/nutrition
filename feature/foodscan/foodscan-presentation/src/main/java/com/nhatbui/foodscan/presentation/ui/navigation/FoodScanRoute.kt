package com.nhatbui.foodscan.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object FoodScanGraph

sealed class FoodScanRoute {
    @Serializable
    data object Scanner: FoodScanRoute()

    @Serializable
    data object Result: FoodScanRoute()
}
