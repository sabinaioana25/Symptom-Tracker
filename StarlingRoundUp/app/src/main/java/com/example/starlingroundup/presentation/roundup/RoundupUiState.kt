package com.example.starlingroundup.presentation.roundup

import com.example.starlingroundup.domain.model.SavingsGoal

sealed class RoundupUiState {
  object Loading : RoundupUiState()
  data class Ready(val roundup: Double, val goals: List<SavingsGoal>) : RoundupUiState()
  object Saved : RoundupUiState()
  data class Error(val message: String) : RoundupUiState()
}
