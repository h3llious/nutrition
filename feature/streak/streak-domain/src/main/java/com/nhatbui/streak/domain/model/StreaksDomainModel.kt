package com.nhatbui.streak.domain.model

data class StreaksDomainModel(
    val currentStreakCount: Int,
    val isCurrentDateInStreak: Boolean,
    val streakDates: List<StreakDateDomainModel>,
    val milestones: List<MilestoneDomainModel>,
    val targetMilestone: MilestoneDomainModel
)
