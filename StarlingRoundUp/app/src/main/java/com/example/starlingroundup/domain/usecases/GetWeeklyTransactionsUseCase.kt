package com.example.starlingroundup.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.starlingroundup.domain.model.Transaction
import com.example.starlingroundup.domain.repository.TransactionsRepository
import jakarta.inject.Inject
import java.time.LocalDate

class GetWeeklyTransactionsUseCase @Inject constructor(
  private val repository: TransactionsRepository
) {

  @RequiresApi(Build.VERSION_CODES.O)
  suspend operator fun invoke(): List<Transaction> {
    val now = LocalDate.now()
    val start = now.minusDays(7)
    return repository.getTransactions(start, now)
  }
}
