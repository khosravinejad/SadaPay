package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoOwnerCache
import com.morteza.sadapay.domain.model.GithubRepoOwnerDomainModel

class GithubRepoOwnerCacheToDomain : Mapper<GithubRepoOwnerCache, GithubRepoOwnerDomainModel>() {
    override fun mapFrom(input: GithubRepoOwnerCache): GithubRepoOwnerDomainModel {
        TODO("Not yet implemented")
    }

    override fun mapFromList(input: List<GithubRepoOwnerCache>): List<GithubRepoOwnerDomainModel> {
        TODO("Not yet implemented")
    }

}