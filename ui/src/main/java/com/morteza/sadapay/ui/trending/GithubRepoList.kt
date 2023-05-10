package com.morteza.sadapay.ui.trending

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.morteza.sadapay.presentation.model.GithubRepoPresentationModel

@Composable
fun GithubRepoList(repos: List<GithubRepoPresentationModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(repos) { repository ->
            GithubRepoItem(
                repository = repository
            )
        }
    }
}