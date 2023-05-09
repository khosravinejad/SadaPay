package com.morteza.sadapay.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.morteza.sadapay.data.source.remote.model.GithubRepoApi

data class GithubReposResponse(
    @SerializedName("items")
    val repositories: List<GithubRepoApi>
)