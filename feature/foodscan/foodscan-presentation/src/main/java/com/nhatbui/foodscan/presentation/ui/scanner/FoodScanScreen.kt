package com.nhatbui.foodscan.presentation.ui.scanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhatbui.foodscan.presentation.presentation.FoodScanViewModel

@Composable
fun FoodScanScreen() {
    val viewModel = hiltViewModel<FoodScanViewModel>()
    Greeting(viewModel.name)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}
