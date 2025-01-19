package com.nhatbui.streak.presentation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatbui.streak.domain.usecase.GetStreaksUseCase
import com.nhatbui.streak.presentation.presentation.model.StreakViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreakViewModel @Inject constructor(
    private val getStreaksUseCase: GetStreaksUseCase
): ViewModel() {

    private val _viewState = MutableStateFlow(StreakViewState())
    val viewState = _viewState.asStateFlow()

    fun fetchStreakDetails() {
        viewModelScope.launch {
            val result = getStreaksUseCase.execute()
            _viewState.update { lastState ->
                lastState.copy(
                    currentStreakCount = result.currentStreakCount,
                    isCurrentDateInStreak = result.isCurrentDateInStreak,
                    streakDates = result.streakDates,
                    milestones = result.milestones,
                    targetMilestone = result.targetMilestone
                )
            }
        }
    }
}
