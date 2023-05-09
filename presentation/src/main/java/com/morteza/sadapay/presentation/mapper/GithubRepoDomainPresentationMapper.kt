package com.morteza.sadapay.presentation.mapper

import com.morteza.sadapay.domain.model.GithubRepoDomainModel
import com.morteza.sadapay.presentation.model.GithubRepoPresentationModel

class GithubRepoDomainPresentationMapper(
    private val ownerDomainPresentationMapper: GithubRepoOwnerDomainPresentationMapper
) :
    DomainPresentationMapper<GithubRepoDomainModel, GithubRepoPresentationModel>() {

    override fun mapToPresentation(input: GithubRepoDomainModel): GithubRepoPresentationModel {
        return GithubRepoPresentationModel(
            id = input.id,
            name = input.name,
            fullName = input.fullName,
            description = input.description,
            language = input.language,
            starsCount = input.starsCount,
            owner = ownerDomainPresentationMapper.mapToPresentation(input.owner)
        )
    }

    override fun mapToDomain(input: GithubRepoPresentationModel): GithubRepoDomainModel {
        return GithubRepoDomainModel(
            id = input.id,
            name = input.name,
            fullName = input.fullName,
            description = input.description,
            language = input.language,
            starsCount = input.starsCount,
            owner = ownerDomainPresentationMapper.mapToDomain(input.owner)
        )
    }
}