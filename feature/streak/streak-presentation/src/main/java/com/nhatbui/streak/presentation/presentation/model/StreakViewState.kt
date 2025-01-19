package com.nhatbui.streak.presentation.presentation.model

import com.nhatbui.streak.domain.model.MilestoneDomainModel
import com.nhatbui.streak.domain.model.StreakDateDomainModel

data class StreakViewState(
    val currentStreakCount: Int = 0,
    val isCurrentDateInStreak: Boolean = false,
    val streakDates: List<StreakDateDomainModel> = emptyList(),
    val milestones: List<MilestoneDomainModel> = emptyList(),
    val targetMilestone: MilestoneDomainModel = MilestoneDomainModel.SevenDayMilestone(false)
)
