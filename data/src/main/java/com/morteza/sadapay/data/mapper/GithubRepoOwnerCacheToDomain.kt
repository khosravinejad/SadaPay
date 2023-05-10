package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoOwnerCache
import com.morteza.sadapay.domain.model.GithubRepoOwnerDomainModel

class GithubRepoOwnerCacheToDomain : Mapper<GithubRepoOwnerCache, GithubRepoOwnerDomainModel>() {
    override fun mapFrom(input: GithubRepoOwnerCache) = GithubRepoOwnerDomainModel(
        id = input.ownerId,
        name = input.ownerName,
        avatarUrl = input.avatarUrl
    )

    override fun mapFromList(input: List<GithubRepoOwnerCache>) = input.map {
        mapFrom(it)
    }
}