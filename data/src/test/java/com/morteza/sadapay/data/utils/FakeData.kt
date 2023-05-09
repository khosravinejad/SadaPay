package com.morteza.sadapay.data.utils

import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import com.morteza.sadapay.data.source.local.model.GithubRepoOwnerCache
import com.morteza.sadapay.data.source.remote.response.GithubReposResponse
import com.morteza.sadapay.data.source.remote.model.GithubRepoApi
import com.morteza.sadapay.data.source.remote.model.GithubRepoOwnerApi
import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.domain.model.GithubRepoOwnerDomainModel

object FakeData {

    fun fakeGithubRepoApiResponse(itemsCount: Int = 10): GithubReposResponse {
        return GithubReposResponse(fakeGithubRepoApiModels(itemsCount))
    }

    fun fakeGithubRepoApiModels(itemsCount: Int = 10): List<GithubRepoApi> {
        val list = mutableListOf<GithubRepoApi>()
        if (itemsCount > 0) {
            for (i in 1..itemsCount) {
                list.add(
                    GithubRepoApi(
                        id = i,
                        name = "repo name $i",
                        fullName = "repo fullName $i",
                        owner = GithubRepoOwnerApi(
                            ownerId = i,
                            ownerName = "repo $i owner name",
                            avatarUrl = ""
                        ),
                        starsCount = i * 1530,
                        description = "repo $i description",
                        language = "repo $i language"
                    )
                )
            }
        }
        return list
    }

    fun fakeCachedGithubRepos(itemsCount: Int = 10): List<GithubRepoCache> {
        val list = mutableListOf<GithubRepoCache>()
        if (itemsCount > 0) {
            for (i in 1..itemsCount) {
                list.add(
                    GithubRepoCache(
                        id = i,
                        name = "repo name $i",
                        fullName = "repo fullName $i",
                        owner = GithubRepoOwnerCache(
                            ownerId = i,
                            ownerName = "repo $i owner name",
                            avatarUrl = ""
                        ),
                        starsCount = i * 1530,
                        description = "repo $i description",
                        language = "repo $i language",
                        lastUpdated = 0
                    )
                )
            }
        }
        return list
    }

    fun fakeGithubReposDomainModel(itemsCount: Int = 10): List<GithubRepoDomainModel> {
        val list = mutableListOf<GithubRepoDomainModel>()
        if (itemsCount > 0) {
            for (i in 1..itemsCount) {
                list.add(
                    GithubRepoDomainModel(
                        id = i,
                        name = "repo name $i",
                        fullName = "repo fullName $i",
                        owner = GithubRepoOwnerDomainModel(
                            id = i,
                            name = "repo $i owner name",
                            avatarUrl = ""
                        ),
                        starsCount = i * 1530,
                        description = "repo $i description",
                        language = "repo $i language"
                    )
                )
            }
        }
        return list
    }
}