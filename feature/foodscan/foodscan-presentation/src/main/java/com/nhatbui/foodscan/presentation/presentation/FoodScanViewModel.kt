package com.nhatbui.foodscan.presentation.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodScanViewModel @Inject constructor (): ViewModel() {
    // temp
    val name = "FoodScan"
}