package com.nhatbui.streak.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nhatbui.common.ui.theme.NeutralHigh
import com.nhatbui.streak.presentation.R
import com.nhatbui.streak.presentation.ui.model.MilestoneUiModel

@Composable
fun MilestoneSection(milestones: List<MilestoneUiModel>) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.titleLarge,
        color = NeutralHigh,
        text = stringResource(R.string.streaks_milestone_section_title)
    )
    Spacer(Modifier.height(12.dp))
    milestones.map { milestone ->
        MilestoneItem(
            gradientColors = milestone.gradientColor,
            badgeIcon = painterResource(milestone.icon),
            isAchieved = milestone.isAchieved,
            milestoneLabel = milestone.count.toString()
        )
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun MilestoneItem(
    gradientColors: List<Color>,
    badgeIcon: Painter,
    isAchieved: Boolean,
    milestoneLabel: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = badgeIcon,
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(Modifier.width(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelMedium,
            text = stringResource(R.string.streaks_milestone_label, milestoneLabel),
            color = NeutralHigh
        )
        if (isAchieved) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF66BB6A)
            )
        }
    }
}
