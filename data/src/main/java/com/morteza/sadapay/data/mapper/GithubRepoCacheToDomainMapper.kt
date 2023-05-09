package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import com.morteza.sadapay.domain.model.GithubRepoDomainModel

class GithubRepoCacheToDomainMapper(
    private val ownerCacheToDomain: GithubRepoOwnerCacheToDomain
) : Mapper<GithubRepoCache, GithubRepoDomainModel>() {
    override fun mapFrom(input: GithubRepoCache): GithubRepoDomainModel {
        TODO("Not yet implemented")
    }

    override fun mapFromList(input: List<GithubRepoCache>): List<GithubRepoDomainModel> {
        TODO("Not yet implemented")
    }

}