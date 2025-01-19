package com.nhatbui.streak.domain.usecase

import com.nhatbui.streak.domain.mapper.StreakDatesGenerator
import com.nhatbui.streak.domain.model.MilestoneDomainModel
import com.nhatbui.streak.domain.model.StreaksDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetStreaksUseCase(
    private val streakDatesGenerator: StreakDatesGenerator
) {
    suspend fun execute(): StreaksDomainModel {
        return withContext(Dispatchers.IO) {
            val (currentStreakCount, isCurrentDateInStreak) = mockCurrentStreak()
            val targetMilestone = MilestoneDomainModel.TenDayMilestone(isAchieved = false)
            val streakDates = streakDatesGenerator.generate(
                currentStreakCount = currentStreakCount,
                isCurrentDateInStreak = isCurrentDateInStreak,
                targetMilestone = targetMilestone
            )
            StreaksDomainModel(
                currentStreakCount = currentStreakCount,
                isCurrentDateInStreak = isCurrentDateInStreak,
                streakDates = streakDates,
                milestones = mockMilestones(),
                targetMilestone = targetMilestone
            )
        }
    }

    private fun mockCurrentStreak(): Pair<Int, Boolean> {
        val currentStreakCount = 5
        val isCurrentDateInStreak = false
        return Pair(currentStreakCount, isCurrentDateInStreak)
    }

    private fun mockMilestones() = listOf(
        MilestoneDomainModel.SevenDayMilestone(isAchieved = true),
        MilestoneDomainModel.TenDayMilestone(isAchieved = false),
        MilestoneDomainModel.TwentyDayMilestone(isAchieved = false),
        MilestoneDomainModel.ThirtyDayMilestone(isAchieved = false)
    )
}
