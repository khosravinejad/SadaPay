package com.morteza.sadapay.presentation.viewmodel

import com.morteza.sadapay.domain.usecase.GetTrendingRepoUseCase
import com.morteza.sadapay.presentation.base.BaseViewModel
import com.morteza.sadapay.presentation.mapper.GithubRepoDomainPresentationMapper
import com.morteza.sadapay.presentation.model.GithubRepoPresentationModel
import com.morteza.sadapay.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// We can define different error type here
sealed class GithubTrendingReposError {
    object NetworkError : GithubTrendingReposError()
    data class UnknownError(val message: String?) : GithubTrendingReposError()
}

sealed class GithubTrendingReposState {
    object Loading : GithubTrendingReposState()
    data class Success(val data: List<GithubRepoPresentationModel>) : GithubTrendingReposState()
    data class Error(val error: GithubTrendingReposError) : GithubTrendingReposState()
}

@HiltViewModel
class GithubTrendingReposViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getTrendingRepoUseCase: GetTrendingRepoUseCase,
    private val githubRepoDomainToPresenterMapper: GithubRepoDomainPresentationMapper
) : BaseViewModel(contextProvider) {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO handle coroutine error
    }

    private val _githubTrendingReposState by lazy {
        MutableStateFlow<GithubTrendingReposState>(GithubTrendingReposState.Loading)
    }
    val githubTrendingReposState: StateFlow<GithubTrendingReposState> = _githubTrendingReposState

    private val _refreshingState by lazy {
        MutableStateFlow(false)
    }
    val refreshingState: StateFlow<Boolean> = _refreshingState

    fun fetchGithubTrendingRepos(forceRefresh: Boolean = false) {
        TODO("Not yet implemented")
    }

    private fun handleUseCaseError(throwable: Throwable): GithubTrendingReposError {
        TODO("Not yet implemented")
    }
}