package com.nhatbui.streak.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhatbui.common.ui.theme.NeutralHigh
import com.nhatbui.common.ui.theme.NeutralMedium
import com.nhatbui.streak.domain.model.StreakDateDomainModel
import com.nhatbui.streak.presentation.R
import com.nhatbui.streak.presentation.ui.model.MilestoneUiModel
import com.nhatbui.streak.presentation.ui.model.StreakWeekday

private const val MAX_ITEM_IN_ROW = 7

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StreakTracker(
    streakDates: List<StreakDateDomainModel>,
    targetMilestone: MilestoneUiModel
) {
    if (streakDates.isNotEmpty()) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFFEDEDED),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = MAX_ITEM_IN_ROW
        ) {
            StreakWeekday.entries.mapIndexed { index, streakWeekday ->
                WeekdayLabel(
                    modifier = Modifier.weight(1f),
                    text = stringResource(streakWeekday.abbreviation),
                    hasLoggedStreak = streakDates[index] is StreakDateDomainModel.LoggedStreak
                )
            }
            streakDates.map { date ->
                when (date) {
                    is StreakDateDomainModel.LoggedStreak -> StreakDate(date.day)
                    is StreakDateDomainModel.CurrentDate -> CurrentDate(date.day)
                    is StreakDateDomainModel.DateInTargetStreak -> TargetDate(date.day)
                    is StreakDateDomainModel.NoStreakDate -> NoStreakDate(date.day)
                }
            }
        }
        TargetMilestone(targetMilestone)
    }
}

@Composable
private fun TargetMilestone(targetMilestone: MilestoneUiModel) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(28.dp)
            .offset(y = (-14).dp)
            .background(
                brush = Brush.horizontalGradient(colors = targetMilestone.gradientColor),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 4.dp)
            .padding(start = 6.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(targetMilestone.icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(Modifier.width(8.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.labelMedium,
            text = stringResource(R.string.streaks_milestone_label, targetMilestone.count),
            color = NeutralHigh
        )
    }
}

@Composable
private fun RowScope.StreakDate(day: Int) {
    Icon(
        modifier = Modifier
            .weight(1f)
            .size(28.dp),
        painter = painterResource(R.drawable.ic_streak_date),
        tint = Color.Unspecified,
        contentDescription = stringResource(R.string.streaks_logged_date_description, day)
    )
}

@Composable
private fun RowScope.CurrentDate(day: Int) {
    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .size(28.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFEDEDED),
                    shape = CircleShape
                )
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFF6F6F6)
                        )
                    ),
                    shape = CircleShape
                )
                .wrapContentHeight(),
            text = day.toString(),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            color = NeutralHigh,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun RowScope.TargetDate(day: Int) {
    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .size(28.dp)
                .wrapContentHeight(),
            text = day.toString(),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            color = NeutralHigh,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowScope.NoStreakDate(day: Int) {
    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .size(28.dp)
                .wrapContentHeight(),
            text = day.toString(),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            color = Color(0xFFD9D9D9),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun WeekdayLabel(
    text: String,
    hasLoggedStreak: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = if (hasLoggedStreak) Color(0xFF5CA660) else NeutralMedium,
        fontWeight = if (hasLoggedStreak) FontWeight.W500 else FontWeight.W400
    )
}
