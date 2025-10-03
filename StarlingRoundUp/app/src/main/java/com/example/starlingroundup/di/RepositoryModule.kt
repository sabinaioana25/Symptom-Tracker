package com.example.starlingroundup.di

import com.example.starlingroundup.data.repository.SavingsGoalsRepositoryImpl
import com.example.starlingroundup.data.repository.TransactionsRepositoryImpl
import com.example.starlingroundup.domain.repository.SavingsGoalsRepository
import com.example.starlingroundup.domain.repository.TransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun bindTransactionsRepository(
    impl: TransactionsRepositoryImpl
  ): TransactionsRepository

  @Binds
  abstract fun bindSavingsGoalRepository(
    impl: SavingsGoalsRepositoryImpl
  ): SavingsGoalsRepository
}
