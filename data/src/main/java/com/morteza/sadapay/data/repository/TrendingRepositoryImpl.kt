package com.morteza.sadapay.data.repository

import com.morteza.sadapay.data.mapper.GithubRepoApiToCacheMapper
import com.morteza.sadapay.data.mapper.GithubRepoCacheToDomainMapper
import com.morteza.sadapay.data.source.local.dao.GithubRepoDao
import com.morteza.sadapay.data.source.remote.RepoApiServices
import com.morteza.sadapay.data.utils.SystemTimestampGenerator
import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.domain.repository.TrendingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class TrendingRepositoryImpl(
    private val apiServices: RepoApiServices,
    private val githubRepoDao: GithubRepoDao,
    private val cacheToDomainMapper: GithubRepoCacheToDomainMapper,
    private val apiToCacheMapper: GithubRepoApiToCacheMapper,
    private val timestampGenerator: SystemTimestampGenerator
) : TrendingRepository {

    private val cachedReposFlow: Flow<List<GithubRepoDomainModel>> by lazy {
        githubRepoDao.getRepositories().map {
            cacheToDomainMapper.mapFromList(it)
        }
    }

    override fun getTrendingRepositories(forceRefresh: Boolean): Flow<List<GithubRepoDomainModel>> {
        runBlocking(Dispatchers.IO) {
            val cacheTimestamp = githubRepoDao.getLastUpdatedTimestamp()
            if (forceRefresh || cacheTimestamp == null || isDataStale(cacheTimestamp)) {
                // Fetch fresh data from API and update cache
                val apiResponse = apiServices.getGithubRepos()
                val newRepos = apiToCacheMapper.mapFromList(apiResponse.repositories)
                githubRepoDao.clearTable()
                githubRepoDao.insertRepositories(newRepos)
            }
        }
        return cachedReposFlow
    }

    override fun searchRepositories(query: String): Flow<List<GithubRepoDomainModel>> {
        return cachedReposFlow.map { list ->
            list.filter {
                it.fullName.contains(query)
            }
        }
    }

    private fun isDataStale(timestamp: Long): Boolean {
        val timeDifferenceMs = timestampGenerator.generateTimestamp() - timestamp
        return timeDifferenceMs > CACHE_DURATION_MS
    }

    companion object {
        const val CACHE_DURATION_MS = 30 * 60 * 1000 // 30 minutes
    }
}