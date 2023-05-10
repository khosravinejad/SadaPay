package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import com.morteza.sadapay.data.source.remote.model.GithubRepoApi
import com.morteza.sadapay.data.utils.TimestampGenerator

class GithubRepoApiToCacheMapper(
    private val ownerApiToCacheMapper: GithubRepoOwnerApiToCacheMapper,
    private val timestampGenerator: TimestampGenerator
) : Mapper<GithubRepoApi, GithubRepoCache>() {
    override fun mapFrom(input: GithubRepoApi) = GithubRepoCache(
        id = input.id,
        name = input.name,
        fullName = input.fullName,
        description = input.description,
        language = input.language ?: "",
        starsCount = input.starsCount,
        lastUpdated = timestampGenerator.generateTimestamp(),
        owner = ownerApiToCacheMapper.mapFrom(input.owner)
    )

    override fun mapFromList(input: List<GithubRepoApi>) = input.map {
        mapFrom(it)
    }
}