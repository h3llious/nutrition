package com.nhatbui.foodscan.presentation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatbui.foodscan.domain.usecase.GetFoodScanProgressUseCase
import com.nhatbui.foodscan.presentation.presentation.model.FoodScanViewState
import com.nhatbui.foodscan.presentation.presentation.model.ScanState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodScanViewModel @Inject constructor(
    private val getFoodScanProgressUseCase: GetFoodScanProgressUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow(FoodScanViewState())
    val viewState = _viewState.asStateFlow()

    fun onScanningFood() {
        _viewState.update { lastState ->
            lastState.copy(scanState = ScanState.IsScanning)
        }
    }

    fun getFoodScanProgress() {
        viewModelScope.launch {
            getFoodScanProgressUseCase.execute { progress ->
                _viewState.update { lastState ->
                    if (progress == 100) {
                        lastState.copy(
                            scanState = ScanState.ScanCompleted,
                            scanProgressPercentage = progress
                        )
                    } else {
                        lastState.copy(scanProgressPercentage = progress)
                    }
                }
            }
        }
    }
}
