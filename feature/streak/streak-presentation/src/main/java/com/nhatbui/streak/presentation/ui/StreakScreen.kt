package com.nhatbui.streak.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nhatbui.common.ui.DefaultBackButton
import com.nhatbui.common.ui.HeaderBar
import com.nhatbui.common.ui.theme.NeutralHigh
import com.nhatbui.common.ui.theme.NeutralMedium
import com.nhatbui.streak.presentation.R
import com.nhatbui.streak.presentation.presentation.StreakViewModel
import com.nhatbui.streak.presentation.ui.component.MilestoneSection
import com.nhatbui.streak.presentation.ui.component.StreakTracker
import com.nhatbui.streak.presentation.ui.mapper.MilestoneUiMapper

@Composable
fun StreakScreen(
    onNavigateBack: () -> Unit
) {
    val viewModel = hiltViewModel<StreakViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val milestoneUiMapper = remember { MilestoneUiMapper() }
    val milestones =
        remember(viewState.milestones) { viewState.milestones.map(milestoneUiMapper::map) }
    val targetMilestone =
        remember(viewState.targetMilestone) { milestoneUiMapper.map(viewState.targetMilestone) }

    LaunchedEffect(Unit) {
        viewModel.fetchStreakDetails()
    }

    StreakActionBar(onNavigateBack)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(96.dp))
        CurrentStreakInfo(viewState.currentStreakCount)

        Spacer(Modifier.height(24.dp))
        StreakTracker(
            streakDates = viewState.streakDates,
            targetMilestone = targetMilestone
        )

        Spacer(Modifier.height(22.dp))
        MilestoneSection(milestones)
    }
}

@Composable
private fun CurrentStreakInfo(currentStreakCount: Int) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .offset(y = 3.dp)
                .blur(3.dp),
            painter = painterResource(R.drawable.ic_streak_current),
            contentDescription = null,
            tint = Color(0xFFFFBF62)
        )
        Icon(
            painter = painterResource(R.drawable.ic_streak_current),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier.padding(top = 30.dp),
            color = NeutralHigh,
            text = currentStreakCount.toString(),
            fontWeight = W600,
            fontSize = 52.sp,
            lineHeight = 52.sp
        )
    }
    Spacer(Modifier.height(18.dp))
    Text(
        style = MaterialTheme.typography.bodyMedium,
        color = NeutralMedium,
        text = stringResource(R.string.streaks_motivate_text_1)
    )
    Text(
        style = MaterialTheme.typography.titleLarge,
        color = NeutralHigh,
        text = stringResource(R.string.streaks_motivate_text_2, currentStreakCount)
    )
    Text(
        style = MaterialTheme.typography.bodyMedium,
        color = NeutralMedium,
        text = stringResource(R.string.streaks_motivate_text_3)
    )
}

@Composable
private fun StreakActionBar(onNavigateBack: () -> Unit) {
    HeaderBar(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0x00FFFFFF)
                    )
                )
            )
            .zIndex(1f),
        title = stringResource(R.string.streaks_title),
        titleColor = NeutralHigh,
        onNavigateBack = onNavigateBack,
        actionButton = {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = stringResource(R.string.streaks_share)
            )
        },
        backButton = {
            DefaultBackButton(onNavigateBack, tintColor = Color(0xFF7B7B7B))
        }
    )
}

