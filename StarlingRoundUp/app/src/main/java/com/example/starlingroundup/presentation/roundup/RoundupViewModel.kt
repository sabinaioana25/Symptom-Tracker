package com.example.starlingroundup.presentation.roundup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starlingroundup.domain.repository.SavingsGoalsRepository
import com.example.starlingroundup.domain.usecases.CalculateRoundupUseCase
import com.example.starlingroundup.domain.usecases.DepositRoundupUseCase
import com.example.starlingroundup.domain.usecases.GetWeeklyTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RoundupViewModel @Inject constructor(
  private val getWeeklyTransactions: GetWeeklyTransactionsUseCase,
  private val calculateRoundup: CalculateRoundupUseCase,
  private val depositRoundup: DepositRoundupUseCase,
  private val savingsRepo: SavingsGoalsRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow<RoundupUiState>(RoundupUiState.Loading)
  val uiState: StateFlow<RoundupUiState> = _uiState

  @RequiresApi(Build.VERSION_CODES.O)
  fun loadRoundup() = viewModelScope.launch {
    try {
      _uiState.value = RoundupUiState.Loading
      val transactions = getWeeklyTransactions()
      val totalRoundup = calculateRoundup(transactions)
      val goals = savingsRepo.getSavingsGoals()
      _uiState.value = RoundupUiState.Ready(totalRoundup, goals)
    } catch (e: Exception) {
      _uiState.value = RoundupUiState.Error("Failed to load data")
    }
  }

  fun saveToGoal(goalId: String, amount: Double) = viewModelScope.launch {
    val success = depositRoundup(goalId, amount)
    _uiState.value =
      if (success) RoundupUiState.Saved
      else RoundupUiState.Error("Deposit failed")
  }
}
