package com.nhatbui.streak.domain.mapper

import com.nhatbui.streak.domain.model.MilestoneDomainModel
import com.nhatbui.streak.domain.model.StreakDateDomainModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

private const val TWO_WEEK_DATE_COUNT = 14L

class StreakDatesGenerator(
    private val now: LocalDate = LocalDate.now()
) {
    fun generate(
        currentStreakCount: Int,
        isCurrentDateInStreak: Boolean,
        targetMilestone: MilestoneDomainModel
    ): List<StreakDateDomainModel> {
        val generatedDates = mutableListOf<StreakDateDomainModel>()
        val pastStreakCount =
            if (isCurrentDateInStreak) currentStreakCount - 1 else currentStreakCount
        val beginningWeekDate = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        val upcomingTargetDateCount = if (isCurrentDateInStreak) {
            targetMilestone.count - currentStreakCount
        } else {
            targetMilestone.count - currentStreakCount - 1
        }

        var processingDate = beginningWeekDate

        for (dateIndex in 1..TWO_WEEK_DATE_COUNT) {
            val dateDifference = ChronoUnit.DAYS.between(processingDate, now)
            val isLoggedDate = dateDifference in 1..pastStreakCount
            val isCurrentDate = dateDifference == 0L
//            val isUpcomingTarget = upcomingTargetDateCount >= -dateDifference
            val isUpcomingTarget =  (-dateDifference) in 0..upcomingTargetDateCount

            generatedDates.add(
                when {
                    isLoggedDate -> StreakDateDomainModel.LoggedStreak(processingDate.dayOfMonth)
                    isCurrentDate -> StreakDateDomainModel.CurrentDate(processingDate.dayOfMonth)
                    isUpcomingTarget -> StreakDateDomainModel.DateInTargetStreak(processingDate.dayOfMonth)
                    else -> StreakDateDomainModel.NoStreakDate(processingDate.dayOfMonth)
                }
            )

            processingDate = processingDate.plusDays(1)
        }
        return generatedDates
    }
}
