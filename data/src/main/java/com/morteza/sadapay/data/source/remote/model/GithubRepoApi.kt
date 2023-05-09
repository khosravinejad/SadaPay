package com.morteza.sadapay.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: GithubRepoOwnerApi,
    @SerializedName("stargazers_count")
    val starsCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: String
)
