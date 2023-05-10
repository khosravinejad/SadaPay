package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import com.morteza.sadapay.domain.model.GithubRepoDomainModel

class GithubRepoCacheToDomainMapper(
    private val ownerCacheToDomain: GithubRepoOwnerCacheToDomain
) : Mapper<GithubRepoCache, GithubRepoDomainModel>() {
    override fun mapFrom(input: GithubRepoCache) = GithubRepoDomainModel(
        id = input.id,
        name = input.name,
        fullName = input.fullName,
        description = input.description,
        language = input.language,
        starsCount = input.starsCount,
        owner = ownerCacheToDomain.mapFrom(input.owner)
    )

    override fun mapFromList(input: List<GithubRepoCache>) = input.map {
        mapFrom(it)
    }
}