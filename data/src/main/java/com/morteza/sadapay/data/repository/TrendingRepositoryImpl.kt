package com.morteza.sadapay.data.repository

import com.morteza.sadapay.data.mapper.GithubRepoApiToCacheMapper
import com.morteza.sadapay.data.mapper.GithubRepoCacheToDomainMapper
import com.morteza.sadapay.data.source.local.dao.GithubRepoDao
import com.morteza.sadapay.data.source.remote.RepoApiServices
import com.morteza.sadapay.data.utils.SystemTimestampGenerator
import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow

class TrendingRepositoryImpl(
    private val apiServices: RepoApiServices,
    private val githubRepoDao: GithubRepoDao,
    private val cacheToDomainMapper: GithubRepoCacheToDomainMapper,
    private val apiToCacheMapper: GithubRepoApiToCacheMapper,
    private val timestampGenerator: SystemTimestampGenerator
) : TrendingRepository {

    override suspend fun getTrendingRepositories(forceRefresh: Boolean): Flow<List<GithubRepoDomainModel>> {
        TODO("Not yet implemented")
    }

    private fun isDataStale(timestamp: Long): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        const val CACHE_DURATION_MS = 30 * 60 * 1000 // 30 minutes
    }
}