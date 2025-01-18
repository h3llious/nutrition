package com.nhatbui.foodscan.domain.usecase

import com.nhatbui.foodscan.domain.model.NutrientDomainModel
import com.nhatbui.foodscan.domain.model.ScanResultDomainModel

class GetScanResultUseCase {
    suspend fun execute(): ScanResultDomainModel {
        return mockScanResult
    }

    private val mockScanResult = ScanResultDomainModel(
        type = "Food",
        name = "Pepperoni Pizza",
        nutritionInCalories = 320,
        macronutrients = listOf(
            NutrientDomainModel.Proteins(20),
            NutrientDomainModel.Carbs( 40),
            NutrientDomainModel.Fats( 10)
        ),
        micronutrients = listOf(
            NutrientDomainModel.VitaminA(10),
            NutrientDomainModel.Calcium(15)
        )
    )
}
