package com.example.starlingroundup.data.api.dto

import com.example.starlingroundup.domain.model.SavingsGoal

data class SavingsGoalsResponseDto(
  val goals: List<SavingsGoalDto>
)

data class SavingsGoalDto(
  val id: String,
  val name: String,
  val target: Double?,
  val saved: Double?
)
