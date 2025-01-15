package com.nhatbui.nutrition.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigateToFoodScan: () -> Unit
) {
    Text(
        modifier = Modifier.clickable {
            onNavigateToFoodScan()
        },
        text = "Home placeholder"
    )
}