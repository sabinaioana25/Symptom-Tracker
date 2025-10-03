package com.example.starlingroundup.domain.usecases

import com.example.starlingroundup.domain.model.Transaction
import jakarta.inject.Inject

class CalculateRoundupUseCase @Inject constructor() {
  operator fun invoke(transactions: List<Transaction>): Double {
    return transactions.sumOf { txn ->
      val decimal = txn.amount % 1
      if (decimal > 0) 1 - decimal else 0.0
    }
  }
}
