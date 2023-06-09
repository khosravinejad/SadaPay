package com.morteza.sadapay.domain.usecase

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetTrendingRepoUseCase(private val trendingRepository: TrendingRepository) :
    BaseUseCase<GetTrendingRepoUseCase.TrendingRepoUseCaseParams, Flow<GetTrendingRepoUseCase.Result>> {

    override suspend fun invoke(params: TrendingRepoUseCaseParams): Flow<Result> {
        return trendingRepository.getTrendingRepositories(params.forceRefresh).map {
            Result.Success(it)
        }.catch { e -> Result.Error(e) }
    }

    data class TrendingRepoUseCaseParams(val forceRefresh: Boolean = false)

    // We can return different restricted types depend on our needs
    sealed class Result {
        data class Success(val data: List<GithubRepoDomainModel>) : Result()
        data class Error(val exception: Throwable) : Result()
    }
}