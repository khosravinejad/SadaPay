package com.morteza.sadapay.presentation.mapper

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.presentation.model.GithubRepoPresentationModel

class GithubRepoDomainPresentationMapper(
    private val ownerDomainPresentationMapper: GithubRepoOwnerDomainPresentationMapper
) :
    DomainPresentationMapper<GithubRepoDomainModel, GithubRepoPresentationModel>() {
    override fun mapToPresentation(input: GithubRepoDomainModel): GithubRepoPresentationModel {
        TODO("Not yet implemented")
    }

    override fun mapToDomain(input: GithubRepoPresentationModel): GithubRepoDomainModel {
        TODO("Not yet implemented")
    }

}