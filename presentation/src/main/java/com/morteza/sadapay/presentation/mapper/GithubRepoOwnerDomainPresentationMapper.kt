package com.morteza.sadapay.presentation.mapper

import com.morteza.sadapay.domain.model.GithubRepoOwnerDomainModel
import com.morteza.sadapay.presentation.model.GithubRepoOwnerPresentationModel

class GithubRepoOwnerDomainPresentationMapper :
    DomainPresentationMapper<GithubRepoOwnerDomainModel, GithubRepoOwnerPresentationModel>() {
    override fun mapToPresentation(input: GithubRepoOwnerDomainModel): GithubRepoOwnerPresentationModel {
        TODO("Not yet implemented")
    }

    override fun mapToDomain(input: GithubRepoOwnerPresentationModel): GithubRepoOwnerDomainModel {
        TODO("Not yet implemented")
    }
}