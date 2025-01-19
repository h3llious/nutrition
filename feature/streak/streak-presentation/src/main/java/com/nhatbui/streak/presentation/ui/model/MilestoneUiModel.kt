package com.nhatbui.streak.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.nhatbui.streak.presentation.R

sealed class MilestoneUiModel(
    open val isAchieved: Boolean,
    val gradientColor: List<Color>,
    @DrawableRes val icon: Int,
    val count: Int
) {
    data class SevenDayBadge(
        override val isAchieved: Boolean
    ) : MilestoneUiModel(
        isAchieved = isAchieved,
        gradientColor = listOf(
            Color(0xFFF0F4F7),
            Color(0xFFF2F5F7)
        ),
        icon = R.drawable.ic_silver_badge,
        count = 7
    )

    data class TenDayBadge(
        override val isAchieved: Boolean
    ) : MilestoneUiModel(
        isAchieved = isAchieved,
        gradientColor = listOf(
            Color(0xFFF5E9DC),
            Color(0xFFFFF8F0)
        ),
        icon = R.drawable.ic_bronze_badge,
        count = 10
    )

    data class TwentyDayBadge(
        override val isAchieved: Boolean
    ) : MilestoneUiModel(
        isAchieved = isAchieved,
        gradientColor = listOf(
            Color(0xFFDCEDF5),
            Color(0xFFEDF6FA)
        ),
        icon = R.drawable.ic_gold_badge,
        count = 20
    )

    data class ThirtyDayBadge(
        override val isAchieved: Boolean
    ) : MilestoneUiModel(
        isAchieved = isAchieved,
        gradientColor = listOf(
            Color(0xFFF5F0FF),
            Color(0xFFF3F0FA)
        ),
        icon = R.drawable.ic_platinum_badge,
        count = 30
    )
}
