package com.example.starlingroundup.data.repository

import com.example.starlingroundup.data.api.PublicApi
import com.example.starlingroundup.data.api.dto.DepositRequestDto
import com.example.starlingroundup.data.mappers.toDomain
import com.example.starlingroundup.domain.model.SavingsGoal
import com.example.starlingroundup.domain.repository.SavingsGoalsRepository
import javax.inject.Inject

class SavingsGoalsRepositoryImpl @Inject constructor(
  private val api: PublicApi
) : SavingsGoalsRepository {
  override suspend fun getSavingsGoals(): List<SavingsGoal> {
    return api.getSavingsGoals().goals.map { it.toDomain() }
  }

  override suspend fun depositToGoal(goalId: String, amount: Double) {
    api.depositToGoal(goalId, DepositRequestDto(amount))
  }
}
