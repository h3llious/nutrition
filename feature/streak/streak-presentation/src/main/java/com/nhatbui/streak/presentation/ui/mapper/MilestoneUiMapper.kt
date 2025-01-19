package com.nhatbui.streak.presentation.ui.mapper

import com.nhatbui.streak.domain.model.MilestoneDomainModel
import com.nhatbui.streak.presentation.ui.model.MilestoneUiModel

class MilestoneUiMapper {
    fun map(milestone: MilestoneDomainModel) = when (milestone) {
        is MilestoneDomainModel.SevenDayMilestone -> MilestoneUiModel.SevenDayBadge(milestone.isAchieved)
        is MilestoneDomainModel.TenDayMilestone -> MilestoneUiModel.TenDayBadge(milestone.isAchieved)
        is MilestoneDomainModel.TwentyDayMilestone -> MilestoneUiModel.TwentyDayBadge(milestone.isAchieved)
        is MilestoneDomainModel.ThirtyDayMilestone -> MilestoneUiModel.ThirtyDayBadge(milestone.isAchieved)
    }
}
