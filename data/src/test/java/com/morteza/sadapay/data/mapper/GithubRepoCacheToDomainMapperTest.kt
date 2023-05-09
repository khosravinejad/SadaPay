package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.utils.FakeData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GithubRepoCacheToDomainMapperTest {

    private lateinit var githubRepoOwnerCacheToDomain: GithubRepoOwnerCacheToDomain
    private lateinit var sut: GithubRepoCacheToDomainMapper

    @Before
    fun setup() {
        githubRepoOwnerCacheToDomain = mock()
        sut = GithubRepoCacheToDomainMapper(githubRepoOwnerCacheToDomain)
    }

    @Test
    fun `mapFrom should map given cacheModel to expected domainModel`() {
        val fakeGithubCache = FakeData.fakeCachedGithubRepos()[0]
        val fakeGithubDomain = FakeData.fakeGithubReposDomainModel()[0]
        given(githubRepoOwnerCacheToDomain.mapFrom(fakeGithubCache.owner)).willReturn(
            fakeGithubDomain.owner
        )

        val result = sut.mapFrom(fakeGithubCache)
        assertEquals(fakeGithubDomain, result)
    }

    @Test
    fun `mapFromList should map given cacheModels to expected domainModels`() {
        val fakeGithubCacheList = FakeData.fakeCachedGithubRepos(1)
        val fakeGithubDomainList = FakeData.fakeGithubReposDomainModel(1)
        given(githubRepoOwnerCacheToDomain.mapFrom(fakeGithubCacheList[0].owner)).willReturn(
            fakeGithubDomainList[0].owner)

        val result = sut.mapFromList(fakeGithubCacheList)
        assertEquals(fakeGithubDomainList, result)
    }
}