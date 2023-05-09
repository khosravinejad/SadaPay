package com.morteza.sadapay.domain.repository

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    suspend fun getTrendingRepositories(forceRefresh: Boolean = false): Flow<List<GithubRepoDomainModel>>
}