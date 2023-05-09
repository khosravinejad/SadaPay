package com.morteza.sadapay.presentation.viewmodel

import com.morteza.sadapay.domain.usecase.GetTrendingRepoUseCase
import com.morteza.sadapay.presentation.mapper.GithubRepoDomainPresentationMapper
import com.morteza.sadapay.presentation.utils.CoroutineTestRule
import com.morteza.sadapay.presentation.utils.FakeData
import com.morteza.sadapay.presentation.utils.TestContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.given
import java.io.IOException

@ExperimentalCoroutinesApi
class GithubTrendingReposViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val dispatcher = TestContextProvider()

    private lateinit var getTrendingRepoUseCase: GetTrendingRepoUseCase
    private lateinit var githubRepoDomainPresentationMapper: GithubRepoDomainPresentationMapper
    private lateinit var sut: GithubTrendingReposViewModel

    @Before
    fun setup() {
        getTrendingRepoUseCase = mock()
        githubRepoDomainPresentationMapper = mock()
        sut = GithubTrendingReposViewModel(
            dispatcher,
            getTrendingRepoUseCase,
            githubRepoDomainPresentationMapper
        )
    }

    @Test
    fun `fetchGithubTrendingRepos with forceRefresh=false should emit Success state with data`() = runTest {
        // Given
        val params = GetTrendingRepoUseCase.TrendingRepoUseCaseParams(false)
        val expectedData = FakeData.fakeGithubReposDomainModel()
        val expectedPresentationData = expectedData.map { githubRepoDomainPresentationMapper.mapToPresentation(it) }
        given(getTrendingRepoUseCase.invoke(params)).willReturn(flowOf(GetTrendingRepoUseCase.Result.Success(expectedData)))

        // When
        sut.fetchGithubTrendingRepos(false)

        // Then
        assertEquals(GithubTrendingReposState.Success(expectedPresentationData), sut.githubTrendingReposState.value)
        assertEquals(false, sut.refreshingState.value)
    }

    @Test
    fun `fetchGithubTrendingRepos with forceRefresh=true should emit Success state with data`() = runTest {
        // Given
        val params = GetTrendingRepoUseCase.TrendingRepoUseCaseParams(true)
        val expectedData = FakeData.fakeGithubReposDomainModel()
        val expectedPresentationData = expectedData.map { githubRepoDomainPresentationMapper.mapToPresentation(it) }
        given(getTrendingRepoUseCase.invoke(params)).willReturn(flowOf(GetTrendingRepoUseCase.Result.Success(expectedData)))

        // When
        sut.fetchGithubTrendingRepos(true)

        // Then
        assertEquals(GithubTrendingReposState.Success(expectedPresentationData), sut.githubTrendingReposState.value)
        assertEquals(false, sut.refreshingState.value)
    }

    @Test
    fun `fetchGithubTrendingRepos should emit Error state when use case throws exception`() = runTest {
        // Given
        val params = GetTrendingRepoUseCase.TrendingRepoUseCaseParams(false)
        val expectedException = IOException()
        given(getTrendingRepoUseCase.invoke(params)).willReturn(flowOf(GetTrendingRepoUseCase.Result.Error(expectedException)))

        // When
        sut.fetchGithubTrendingRepos(false)

        // Then
        assertEquals(GithubTrendingReposState.Error(GithubTrendingReposError.NetworkError), sut.githubTrendingReposState.value)
        assertEquals(false, sut.refreshingState.value)
    }

}