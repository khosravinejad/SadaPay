package com.morteza.sadapay.presentation.mapper

import com.morteza.sadapay.domain.model.GithubRepoOwnerDomainModel
import com.morteza.sadapay.presentation.model.GithubRepoOwnerPresentationModel

class GithubRepoOwnerDomainPresentationMapper :
    DomainPresentationMapper<GithubRepoOwnerDomainModel, GithubRepoOwnerPresentationModel>() {

    override fun mapToPresentation(input: GithubRepoOwnerDomainModel): GithubRepoOwnerPresentationModel {
        return GithubRepoOwnerPresentationModel(
            id = input.id,
            name = input.name,
            avatarUrl = input.avatarUrl
        )
    }

    override fun mapToDomain(input: GithubRepoOwnerPresentationModel): GithubRepoOwnerDomainModel {
        return GithubRepoOwnerDomainModel(
            id = input.id,
            name = input.name,
            avatarUrl = input.avatarUrl
        )
    }
}