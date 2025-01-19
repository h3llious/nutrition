package com.nhatbui.streak.domain.model

sealed class MilestoneDomainModel(
    open val isAchieved: Boolean,
    val count: Int
) {
    data class SevenDayMilestone(
        override val isAchieved: Boolean
    ) : MilestoneDomainModel(isAchieved, 7)

    data class TenDayMilestone(
        override val isAchieved: Boolean
    ) : MilestoneDomainModel(isAchieved, 10)

    data class TwentyDayMilestone(
        override val isAchieved: Boolean
    ) : MilestoneDomainModel(isAchieved, 20)

    data class ThirtyDayMilestone(
        override val isAchieved: Boolean
    ) : MilestoneDomainModel(isAchieved, 30)
}
