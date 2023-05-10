package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoOwnerCache
import com.morteza.sadapay.data.source.remote.model.GithubRepoOwnerApi

class GithubRepoOwnerApiToCacheMapper : Mapper<GithubRepoOwnerApi, GithubRepoOwnerCache>() {
    override fun mapFrom(input: GithubRepoOwnerApi) = GithubRepoOwnerCache(
        ownerId = input.ownerId,
        ownerName = input.ownerName,
        avatarUrl = input.avatarUrl
    )

    override fun mapFromList(input: List<GithubRepoOwnerApi>) = input.map {
        mapFrom(it)
    }
}