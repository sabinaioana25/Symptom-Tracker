package com.example.starlingroundup.domain.repository

import com.example.starlingroundup.domain.model.SavingsGoal

interface SavingsGoalsRepository {
  suspend fun getSavingsGoals(): List<SavingsGoal>
  suspend fun depositToGoal(goalId: String, amount: Double)
}
