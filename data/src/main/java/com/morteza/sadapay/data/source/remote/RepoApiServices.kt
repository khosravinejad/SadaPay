package com.morteza.sadapay.data.source.remote

import com.morteza.sadapay.data.source.remote.response.GithubReposResponse
import retrofit2.http.GET

interface RepoApiServices {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getGithubRepos(): GithubReposResponse
}