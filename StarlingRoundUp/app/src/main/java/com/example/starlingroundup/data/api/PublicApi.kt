package com.example.starlingroundup.data.api

import com.example.starlingroundup.data.api.dto.DepositRequestDto
import com.example.starlingroundup.data.api.dto.DepositResponseDto
import com.example.starlingroundup.data.api.dto.SavingsGoalsResponseDto
import com.example.starlingroundup.data.api.dto.TransactionResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PublicApi {

  @GET("transactions")
  suspend fun getTransactions(
    @Query("from") start: String,
    @Query("to") end: String
  ): TransactionResponseDto

  @GET("savings-goals")
  suspend fun getSavingsGoals(): SavingsGoalsResponseDto

  @POST("savings-goals/{id}/add-money")
  suspend fun depositToGoal(
    @Path("id") goalId:String,
    @Body body: DepositRequestDto
  ): DepositResponseDto
}
