package com.example.starlingroundup.data.mappers

import com.example.starlingroundup.data.api.dto.SavingsGoalDto
import com.example.starlingroundup.data.api.dto.TransactionDto
import com.example.starlingroundup.domain.model.SavingsGoal
import com.example.starlingroundup.domain.model.Transaction

fun TransactionDto.toDomain() = Transaction(
  id = id,
  amount = amount,
  currency = currency
)

fun SavingsGoalDto.toDomain() = SavingsGoal(
  id = id,
  name = name,
  target = target,
  saved = saved
)
