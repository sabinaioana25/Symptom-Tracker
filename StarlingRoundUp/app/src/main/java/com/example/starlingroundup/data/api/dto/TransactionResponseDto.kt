package com.example.starlingroundup.data.api.dto

data class TransactionResponseDto(val items: List<TransactionDto>)

data class TransactionDto(
  val id: String,
  val amount: Double,
  val currency: String
)
