package com.morteza.sadapay.domain.model

data class GithubRepoDomainModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
    val starsCount: Int,
    val owner: GithubRepoOwnerDomainModel
)