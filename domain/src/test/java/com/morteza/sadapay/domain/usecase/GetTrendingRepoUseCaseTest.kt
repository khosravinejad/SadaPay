package com.morteza.sadapay.domain.usecase

import com.morteza.sadapay.domain.repository.TrendingRepository
import com.morteza.sadapay.domain.utils.CoroutineTestRule
import com.morteza.sadapay.domain.utils.FakeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.io.IOException

@ExperimentalCoroutinesApi
class GetTrendingRepoUseCaseTest {

    @get:Rule
    val testRule = CoroutineTestRule()

    private lateinit var repository: TrendingRepository
    private lateinit var sut: GetTrendingRepoUseCase

    @Before
    fun setup() {
        repository = mock()
        sut = GetTrendingRepoUseCase(repository)
    }

    @Test
    fun `invoke with forceRefresh=false should emit Result_Success with data`() = runTest {
        // given
        val params = GetTrendingRepoUseCase.TrendingRepoUseCaseParams(forceRefresh = false)
        val expectedData = FakeData.fakeGithubReposDomainModel()

        // mock repository behavior
        given(repository.getTrendingRepositories(false)).willReturn(flowOf(expectedData))

        // when
        val resultFlow = sut(params)

        // then
        val result = resultFlow.first()
        assertTrue(result is GetTrendingRepoUseCase.Result.Success)
        assertEquals(10, (result as GetTrendingRepoUseCase.Result.Success).data.size)
        assertEquals(expectedData, result.data)
    }

    @Test
    fun `invoke with forceRefresh=true should emit Result_Success with data`() = runTest {
        // given
        val params = GetTrendingRepoUseCase.TrendingRepoUseCaseParams(forceRefresh = true)
        val expectedData = FakeData.fakeGithubReposDomainModel()

        // mock repository behavior
        given(repository.getTrendingRepositories(true)).willReturn(flowOf(expectedData))

        // when
        val resultFlow = sut(params)

        // then
        val result = resultFlow.first()
        assertTrue(result is GetTrendingRepoUseCase.Result.Success)
        assertEquals(10, (result as GetTrendingRepoUseCase.Result.Success).data.size)
        assertEquals(expectedData, result.data)
    }

    @Test
    fun `invoke should emit Result_Error when repository throws exception`() = runTest {
        // given
        val params = GetTrendingRepoUseCase.TrendingRepoUseCaseParams(forceRefresh = false)
        val expectedException = IOException()

        // mock repository behavior
        given(repository.getTrendingRepositories(false)).will { throw expectedException }


        // when
        val resultFlow = sut(params)

        // then
        val result = resultFlow.first()
        assertTrue(result is GetTrendingRepoUseCase.Result.Error)
        assertEquals(expectedException, (result as GetTrendingRepoUseCase.Result.Error).exception)
    }

}