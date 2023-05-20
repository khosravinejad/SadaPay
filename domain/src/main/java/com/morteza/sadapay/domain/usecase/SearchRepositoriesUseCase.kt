package com.morteza.sadapay.domain.usecase

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SearchRepositoriesUseCase(private val trendingRepository: TrendingRepository) :
    BaseUseCase<SearchRepositoriesUseCase.SearchReposUseCaseParams, Flow<SearchRepositoriesUseCase.Result>> {

    data class SearchReposUseCaseParams(val query: String)

    // We can return different restricted types depend on our needs
    sealed class Result {
        data class Success(val data: List<GithubRepoDomainModel>) : Result()
        data class Error(val exception: Throwable) : Result()
    }

    override suspend fun invoke(params: SearchReposUseCaseParams): Flow<Result> {
        return trendingRepository.searchRepositories(params.query).map {
            Result.Success(it)
        }.catch { e -> Result.Error(e) }
    }
}