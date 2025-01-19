package com.nhatbui.streak.presentation.ui.model

import androidx.annotation.StringRes
import com.nhatbui.streak.presentation.R

enum class StreakWeekday(@StringRes val abbreviation: Int) {
    MONDAY(R.string.streaks_weekday_abbrev_monday),
    TUESDAY(R.string.streaks_weekday_abbrev_tuesday),
    WEDNESDAY(R.string.streaks_weekday_abbrev_wednesday),
    THURSDAY(R.string.streaks_weekday_abbrev_thursday),
    FRIDAY(R.string.streaks_weekday_abbrev_friday),
    SATURDAY(R.string.streaks_weekday_abbrev_saturday),
    SUNDAY(R.string.streaks_weekday_abbrev_sunday),
}
