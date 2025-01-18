package com.nhatbui.foodscan.domain.model

data class ScanResultDomainModel(
    val type: String,
    val name: String,
    val nutritionInCalories: Int,
    val macronutrients: List<NutrientDomainModel>,
    val micronutrients: List<NutrientDomainModel>
)
