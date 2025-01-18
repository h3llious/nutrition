package com.nhatbui.foodscan.presentation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatbui.foodscan.domain.usecase.GetScanResultUseCase
import com.nhatbui.foodscan.presentation.presentation.model.ScanResultViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanResultViewModel @Inject constructor(
    private val getScanResultUseCase: GetScanResultUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow(ScanResultViewState())
    val viewState = _viewState.asStateFlow()

    fun getScanResult() {
        viewModelScope.launch {
            val result = getScanResultUseCase.execute()
            _viewState.update { lastState ->
                lastState.copy(
                    type = result.type,
                    name = result.name,
                    nutritionInCalories = result.nutritionInCalories,
                    macronutrients = result.macronutrients,
                    micronutrients = result.micronutrients
                )
            }
        }
    }
}
