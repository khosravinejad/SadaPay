package com.morteza.sadapay.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.morteza.sadapay.data.mapper.GithubRepoApiToCacheMapper
import com.morteza.sadapay.data.mapper.GithubRepoCacheToDomainMapper
import com.morteza.sadapay.data.source.local.AppDatabase
import com.morteza.sadapay.data.source.local.dao.GithubRepoDao
import com.morteza.sadapay.data.source.remote.RepoApiServices
import com.morteza.sadapay.data.source.remote.response.GithubReposResponse
import com.morteza.sadapay.data.utils.CoroutineTestRule
import com.morteza.sadapay.data.utils.FakeData
import com.morteza.sadapay.data.utils.SystemTimestampGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.given
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class TrendingRepositoryImplTest {

    @get:Rule
    val testRule = CoroutineTestRule()

    private val dispatcher = testRule.dispatcher
    private lateinit var database: AppDatabase
    private lateinit var githubRepoDao: GithubRepoDao
    private lateinit var apiServices: RepoApiServices
    private lateinit var cacheToDomainMapper: GithubRepoCacheToDomainMapper
    private lateinit var apiToCacheMapper: GithubRepoApiToCacheMapper
    private lateinit var timestampGenerator: SystemTimestampGenerator
    private lateinit var sut: TrendingRepositoryImpl

    @Before
    fun setup() {
        apiServices = mock()
        cacheToDomainMapper = mock()
        apiToCacheMapper = mock()
        timestampGenerator = mock()
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()
        githubRepoDao = spy(database.githubRepoDao)
        sut = TrendingRepositoryImpl(
            apiServices,
            githubRepoDao,
            cacheToDomainMapper,
            apiToCacheMapper,
            timestampGenerator
        )
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        dispatcher.run {
            database.clearAllTables()
        }
        database.close()
    }

    @Test
    fun `getTrendingRepositories should fetch from API if cache is null`() = runTest {
        // Arrange
        val forceRefresh = false
        val fakeApiResponse = FakeData.fakeGithubRepoApiResponse(1)
        val fakeCachedRepos = FakeData.fakeCachedGithubRepos(1)
        val expectedResult = FakeData.fakeGithubReposDomainModel(1)

        given(timestampGenerator.generateTimestamp()).willReturn(fakeCachedRepos[0].lastUpdated)
        given(apiServices.getGithubRepos()).willReturn(fakeApiResponse)
        given(apiToCacheMapper.mapFromList(fakeApiResponse.repositories)).willReturn(fakeCachedRepos)
        given(cacheToDomainMapper.mapFromList(fakeCachedRepos)).willReturn(
            expectedResult
        )
        given(cacheToDomainMapper.mapFrom(fakeCachedRepos[0])).willReturn(
            expectedResult[0]
        )

        // Act
        val result = sut.getTrendingRepositories(forceRefresh).first()

        // Assert
        verify(apiServices).getGithubRepos()
        assertEquals(expectedResult, result)
    }

    @Test
    fun `getTrendingRepositories should fetch from API if cache is stale`() = runTest {
        // Arrange
        val cacheTimestamp =
            timestampGenerator.generateTimestamp() - (TrendingRepositoryImpl.CACHE_DURATION_MS + 60)
        val forceRefresh = false
        val fakeApiResponse = FakeData.fakeGithubRepoApiResponse()
        val fakeCachedRepos = FakeData.fakeCachedGithubRepos()
        val fakeGithubRepoDomainModels = FakeData.fakeGithubReposDomainModel()

        given(githubRepoDao.getLastUpdatedTimestamp()).willReturn(cacheTimestamp)
        given(apiServices.getGithubRepos()).willReturn(fakeApiResponse)
        given(apiToCacheMapper.mapFromList(fakeApiResponse.repositories)).willReturn(fakeCachedRepos)
        given(cacheToDomainMapper.mapFromList(fakeCachedRepos)).willReturn(
            fakeGithubRepoDomainModels
        )

        // Act
        sut.getTrendingRepositories(forceRefresh).first()

        // Assert
        verify(apiServices).getGithubRepos()
        verify(githubRepoDao).clearTable()
        verify(githubRepoDao).insertRepositories(fakeCachedRepos)
    }

    @Test
    fun `getTrendingRepositories should not fetch from API if cache is fresh and forceRefresh is false`() =
        runTest {
            // Arrange
            val cacheTimestamp = timestampGenerator.generateTimestamp()
            val forceRefresh = false

            given(githubRepoDao.getLastUpdatedTimestamp()).willReturn(cacheTimestamp)

            // Act
            sut.getTrendingRepositories(forceRefresh).first()

            // Assert
            verify(apiServices, never()).getGithubRepos()
            verify(githubRepoDao, never()).clearTable()
            verify(githubRepoDao, never()).insertRepositories(emptyList())
        }

    @Test
    fun `getTrendingRepositories should fetch from API if cache is fresh and forceRefresh is true`() =
        runTest {
            // Arrange
            val cacheTimestamp = timestampGenerator.generateTimestamp()
            val forceRefresh = true

            given(githubRepoDao.getLastUpdatedTimestamp()).willReturn(cacheTimestamp)
            given(apiServices.getGithubRepos()).willReturn(GithubReposResponse(emptyList()))

            // Act
            sut.getTrendingRepositories(forceRefresh).first()

            // Assert
            verify(apiServices).getGithubRepos()
            verify(githubRepoDao).clearTable()
            verify(githubRepoDao).insertRepositories(emptyList())
        }

    @Test
    fun `searchRepositories returns filtered repositories`() =
        runTest {
            // Arrange

            val query = "repo fullName 1"
            val cachedRepos = FakeData.fakeCachedGithubRepos()
            val filteredRepos = FakeData.fakeGithubReposDomainModel(1)

            given(githubRepoDao.getRepositories()).willReturn(flowOf(cachedRepos))
            given(cacheToDomainMapper.mapFromList(anyOrNull())).willReturn(filteredRepos)

            // Act
            val result = sut.searchRepositories(query).first()

            // Assert
            verify(githubRepoDao).getRepositories()
            assertEquals(filteredRepos, result)
        }
}