package com.morteza.sadapay.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoOwnerApi(
    @SerializedName("id")
    val ownerId: Int,
    @SerializedName("login")
    val ownerName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
