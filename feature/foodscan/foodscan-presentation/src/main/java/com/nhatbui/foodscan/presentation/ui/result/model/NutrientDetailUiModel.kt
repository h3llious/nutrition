package com.nhatbui.foodscan.presentation.ui.result.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.nhatbui.foodscan.presentation.R

sealed class NutrientDetailUiModel(
    @DrawableRes val iconRes: Int,
    val tintColor: Color,
    val name: @Composable () -> String,
    open val value: Int
) {
    data class Proteins(
        override val value: Int,
    ) : NutrientDetailUiModel(
        iconRes = R.drawable.ic_proteins,
        tintColor = Color(0xFFA883FF),
        name = { stringResource(R.string.nutrient_proteins) },
        value = value
    )

    data class Carbs(
        override val value: Int,
    ) : NutrientDetailUiModel(
        iconRes = R.drawable.ic_carbs,
        tintColor = Color(0xFFFFA726),
        name = { stringResource(R.string.nutrient_carbs) },
        value = value
    )

    data class Fats(
        override val value: Int,
    ) : NutrientDetailUiModel(
        iconRes = R.drawable.ic_fats,
        tintColor = Color(0xFF66BB6A),
        name = { stringResource(R.string.nutrient_fats) },
        value = value
    )

    data class VitaminA(
        override val value: Int,
    ) : NutrientDetailUiModel(
        iconRes = R.drawable.ic_vitamin_a,
        tintColor = Color(0xFFFFA726),
        name = { stringResource(R.string.nutrient_vitamin_a) },
        value = value
    )

    data class Calcium(
        override val value: Int,
    ) : NutrientDetailUiModel(
        iconRes = R.drawable.ic_calcium,
        tintColor = Color(0xFF66BB6A),
        name = { stringResource(R.string.nutrient_calcium) },
        value = value
    )
}
