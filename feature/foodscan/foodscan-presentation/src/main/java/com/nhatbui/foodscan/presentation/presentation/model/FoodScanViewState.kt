package com.nhatbui.foodscan.presentation.presentation.model

data class FoodScanViewState(
    val scanState: ScanState = ScanState.NotScanning,
    val scanProgressPercentage: Int = 0
)
