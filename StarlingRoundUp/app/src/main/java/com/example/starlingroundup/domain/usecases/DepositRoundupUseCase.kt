package com.example.starlingroundup.domain.usecases

import com.example.starlingroundup.domain.repository.SavingsGoalsRepository
import jakarta.inject.Inject

class DepositRoundupUseCase @Inject constructor(
  private val savingsRepo: SavingsGoalsRepository
) {
  suspend operator fun invoke(goalId: String, amount: Double): Boolean {
    return try {
      savingsRepo.depositToGoal(goalId, amount)
      true
    } catch (e: Exception) {
      false
    }
  }
}
