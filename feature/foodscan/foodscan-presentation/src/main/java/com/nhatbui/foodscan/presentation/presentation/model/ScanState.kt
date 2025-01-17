package com.nhatbui.foodscan.presentation.presentation.model

sealed class ScanState {
    data object NotScanning : ScanState()
    data object IsScanning : ScanState()
    data object ScanCompleted : ScanState()
}