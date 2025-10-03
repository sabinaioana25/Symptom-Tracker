package com.example.starlingroundup.domain.model

data class SavingsGoal(
  val id: String,
  val name: String,
  val target: Double?,
  val saved: Double?
)
