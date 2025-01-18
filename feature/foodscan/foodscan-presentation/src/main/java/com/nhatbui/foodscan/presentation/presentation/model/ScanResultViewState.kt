package com.nhatbui.foodscan.presentation.presentation.model

import com.nhatbui.foodscan.domain.model.NutrientDomainModel

data class ScanResultViewState(
    val type: String = "",
    val name: String = "",
    val nutritionInCalories: Int = 0,
    val macronutrients: List<NutrientDomainModel> = emptyList(),
    val micronutrients: List<NutrientDomainModel> = emptyList()
)
