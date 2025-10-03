package com.example.starlingroundup.data.repository

import com.example.starlingroundup.data.api.PublicApi
import com.example.starlingroundup.data.mappers.toDomain
import com.example.starlingroundup.domain.model.Transaction
import com.example.starlingroundup.domain.repository.TransactionsRepository
import jakarta.inject.Inject
import java.time.LocalDate

class TransactionsRepositoryImpl @Inject constructor(
  private val api: PublicApi
) :  TransactionsRepository {
  override suspend fun getTransactions(
    startDate: LocalDate,
    endDate: LocalDate
  ): List<Transaction> {
    val response = api.getTransactions(startDate.toString(), endDate.toString())
    return response.items.map { dto -> dto.toDomain()}
  }
}
