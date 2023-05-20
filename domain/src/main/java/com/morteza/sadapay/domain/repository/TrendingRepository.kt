package com.morteza.sadapay.domain.repository

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getTrendingRepositories(forceRefresh: Boolean = false): Flow<List<GithubRepoDomainModel>>
    fun searchRepositories(query: String): Flow<List<GithubRepoDomainModel>>
}