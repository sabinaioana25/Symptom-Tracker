package com.example.starlingroundup.di

import com.example.starlingroundup.domain.repository.SavingsGoalsRepository
import com.example.starlingroundup.domain.repository.TransactionsRepository
import com.example.starlingroundup.domain.usecases.CalculateRoundupUseCase
import com.example.starlingroundup.domain.usecases.DepositRoundupUseCase
import com.example.starlingroundup.domain.usecases.GetWeeklyTransactionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  fun provideGetWeeklyTransactionsUseCase(
    repo: TransactionsRepository
  ) = GetWeeklyTransactionsUseCase(repo)

  @Provides
  fun provideCalculateRoundupUseCase() =
    CalculateRoundupUseCase()

  @Provides
  fun provideDepositRoundupUseCase(repo: SavingsGoalsRepository) =
    DepositRoundupUseCase(repo)
}
