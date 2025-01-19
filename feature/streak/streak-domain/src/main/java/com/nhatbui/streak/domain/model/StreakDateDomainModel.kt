package com.nhatbui.streak.domain.model

sealed class StreakDateDomainModel(
    open val day: Int
) {
    data class LoggedStreak(
        override val day: Int
    ) : StreakDateDomainModel(day)

    data class CurrentDate(
        override val day: Int
    ) : StreakDateDomainModel(day)

    data class DateInTargetStreak(
        override val day: Int
    ) : StreakDateDomainModel(day)

    data class NoStreakDate(
        override val day: Int
    ) : StreakDateDomainModel(day)
}
