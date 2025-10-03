package com.example.starlingroundup.di

import com.example.starlingroundup.data.api.PublicApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://api.yourporivder.com/") // TODO: replace
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providePublicApi(retrofit: Retrofit): PublicApi =
    retrofit.create(PublicApi::class.java)
}
