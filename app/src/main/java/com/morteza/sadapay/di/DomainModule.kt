package com.morteza.sadapay.di

import com.morteza.sadapay.domain.repository.TrendingRepository
import com.morteza.sadapay.domain.usecase.GetTrendingRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesGetTrendingRepoUseCase(
        trendingRepository: TrendingRepository
    ): GetTrendingRepoUseCase = GetTrendingRepoUseCase(trendingRepository)
}