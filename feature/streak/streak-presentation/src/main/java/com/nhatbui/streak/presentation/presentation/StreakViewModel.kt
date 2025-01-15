package com.nhatbui.streak.presentation.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StreakViewModel @Inject constructor(): ViewModel() {
    // temp
    val name = "Streaks"
}