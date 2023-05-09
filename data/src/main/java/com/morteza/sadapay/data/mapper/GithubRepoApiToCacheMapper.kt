package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import com.morteza.sadapay.data.source.remote.model.GithubRepoApi
import com.morteza.sadapay.data.utils.TimestampGenerator

class GithubRepoApiToCacheMapper(
    private val ownerApiToCacheMapper: GithubRepoOwnerApiToCacheMapper,
    private val timestampGenerator: TimestampGenerator
) : Mapper<GithubRepoApi, GithubRepoCache>() {
    override fun mapFrom(input: GithubRepoApi): GithubRepoCache {
        TODO("Not yet implemented")
    }

    override fun mapFromList(input: List<GithubRepoApi>): List<GithubRepoCache> {
        TODO("Not yet implemented")
    }

}