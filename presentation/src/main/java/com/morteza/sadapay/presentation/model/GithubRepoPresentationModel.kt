package com.morteza.sadapay.presentation.model

data class GithubRepoPresentationModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
    val starsCount: Int,
    val owner: GithubRepoOwnerPresentationModel
)