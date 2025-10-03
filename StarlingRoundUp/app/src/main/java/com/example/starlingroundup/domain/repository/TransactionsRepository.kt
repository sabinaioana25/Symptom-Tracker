package com.example.starlingroundup.domain.repository

import com.example.starlingroundup.domain.model.Transaction
import java.time.LocalDate

interface TransactionsRepository {
  suspend fun getTransactions(startDate: LocalDate, endDate: LocalDate): List<Transaction>
}
