package com.morteza.sadapay.domain.utils

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.domain.model.GithubRepoOwnerDomainModel

object FakeData {

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