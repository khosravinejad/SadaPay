package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.utils.FakeData
import com.morteza.sadapay.data.utils.SystemTimestampGenerator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GithubRepoApiToCacheMapperTest {

    private lateinit var githubRepoOwnerApiToCacheMapper: GithubRepoOwnerApiToCacheMapper
    private lateinit var timestampGenerator: SystemTimestampGenerator
    private lateinit var sut: GithubRepoApiToCacheMapper

    @Before
    fun setup() {
        githubRepoOwnerApiToCacheMapper = mock()
        timestampGenerator = mock()
        sut = GithubRepoApiToCacheMapper(githubRepoOwnerApiToCacheMapper, timestampGenerator)
    }

    @Test
    fun `mapFrom should map given apiModel to expected cacheModel`() {
        val fakeGithubApi = FakeData.fakeGithubRepoApiModels()[0]
        val fakeGithubCache = FakeData.fakeCachedGithubRepos()[0]
        given(githubRepoOwnerApiToCacheMapper.mapFrom(fakeGithubApi.owner)).willReturn(
            fakeGithubCache.owner
        )

        val result = sut.mapFrom(fakeGithubApi)
        assertEquals(fakeGithubCache, result)
    }

    @Test
    fun `mapFromList should map given apiModels to expected cacheModels`() {
        val fakeGithubApiList = FakeData.fakeGithubRepoApiModels(1)
        val fakeGithubCacheList = FakeData.fakeCachedGithubRepos(1)
        given(githubRepoOwnerApiToCacheMapper.mapFrom(fakeGithubApiList[0].owner)).willReturn(
            fakeGithubCacheList[0].owner)

        val result = sut.mapFromList(fakeGithubApiList)
        assertEquals(fakeGithubCacheList, result)
    }
}