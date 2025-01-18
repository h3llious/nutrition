package com.nhatbui.foodscan.presentation.ui.result.mapper

import com.nhatbui.foodscan.presentation.ui.result.model.CircularSegmentModel
import com.nhatbui.foodscan.presentation.ui.result.model.NutrientsUiModel

class NutrientDetailsToCircularSegmentMapper {
    fun map(nutrients: NutrientsUiModel): List<CircularSegmentModel> {
        return nutrients.details.map { nutrient ->
            CircularSegmentModel(weight = nutrient.value, color = nutrient.tintColor)
        }
    }
}
