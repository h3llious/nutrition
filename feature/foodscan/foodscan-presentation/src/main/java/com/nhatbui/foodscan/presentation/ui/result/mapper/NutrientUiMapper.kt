package com.nhatbui.foodscan.presentation.ui.result.mapper

import com.nhatbui.foodscan.domain.model.NutrientDomainModel
import com.nhatbui.foodscan.presentation.ui.result.model.NutrientDetailUiModel
import com.nhatbui.foodscan.presentation.ui.result.model.NutrientsUiModel

class NutrientUiMapper {
    fun map(nutrients: List<NutrientDomainModel>): NutrientsUiModel {
        return NutrientsUiModel(
            total = nutrients.sumOf { it.value },
            details = nutrients.map { detail ->
                when (detail) {
                    is NutrientDomainModel.Proteins -> NutrientDetailUiModel.Proteins(detail.value)
                    is NutrientDomainModel.Carbs -> NutrientDetailUiModel.Carbs(detail.value)
                    is NutrientDomainModel.Fats -> NutrientDetailUiModel.Fats(detail.value)
                    is NutrientDomainModel.VitaminA -> NutrientDetailUiModel.VitaminA(detail.value)
                    is NutrientDomainModel.Calcium -> NutrientDetailUiModel.Calcium(detail.value)
                }
            }
        )
    }
}
