package com.morteza.sadapay.ui.trending

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.morteza.sadapay.presentation.viewmodel.GithubTrendingReposError
import com.morteza.sadapay.presentation.viewmodel.GithubTrendingReposState
import com.morteza.sadapay.presentation.viewmodel.GithubTrendingReposViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Composable
fun TrendingMainScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Trending",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                elevation = AppBarDefaults.TopAppBarElevation,
                backgroundColor = MaterialTheme.colors.background
            )
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                TrendingScreen(navController)
            }
        }
    )
}

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterialApi::class)
@Composable
fun TrendingScreen(navController: NavController) {
    // We will use navController to navigate future screen by using Screen routes
    val viewModel = hiltViewModel<GithubTrendingReposViewModel>()
    val uiState by viewModel.githubTrendingReposState.collectAsState()
    val refreshingState by viewModel.refreshingState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshingState,
        onRefresh = { viewModel.fetchGithubTrendingRepos(true) }
    )
    viewModel.fetchGithubTrendingRepos()

    when (uiState) {
        is GithubTrendingReposState.Loading -> {
            TrendingLoading()
        }
        is GithubTrendingReposState.Success -> {
            val repos = (uiState as GithubTrendingReposState.Success).data
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                GithubRepoList(repos = repos)
                PullRefreshIndicator(
                    refreshingState,
                    pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }
        is GithubTrendingReposState.Error -> {
            val errorMessage =
                when (val error = (uiState as GithubTrendingReposState.Error).error) {
                    is GithubTrendingReposError.NetworkError -> {
                        "Please check your internet connection."
                    }
                    is GithubTrendingReposError.UnknownError -> {
                        error.message ?: "Unknown error occurred. Please try again."
                    }
                }
            RetryScreen(errorMessage) {
                viewModel.fetchGithubTrendingRepos()
            }
        }
    }
}
