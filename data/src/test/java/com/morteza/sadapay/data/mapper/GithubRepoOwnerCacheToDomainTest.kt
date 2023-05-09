package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.utils.FakeData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GithubRepoOwnerCacheToDomainTest {
    private lateinit var sut: GithubRepoOwnerCacheToDomain

    @Before
    fun setup() {
        sut = GithubRepoOwnerCacheToDomain()
    }

    @Test
    fun `mapFrom should map given ownerCacheModel to expected ownerDomainModel`() {
        val fakeGithubOwnerCache = FakeData.fakeCachedGithubRepos()[0].owner
        val fakeGithubOwnerDomain = FakeData.fakeGithubReposDomainModel()[0].owner

        val result = sut.mapFrom(fakeGithubOwnerCache)
        assertEquals(fakeGithubOwnerDomain, result)
    }

    @Test
    fun `mapFromList should map given ownerCacheModels to expected ownerDomainModels`() {
        val fakeGithubOwnerCacheList = FakeData.fakeCachedGithubRepos(1).map { it.owner }
        val fakeGithubOwnerDomainList = FakeData.fakeGithubReposDomainModel(1).map { it.owner }

        val result = sut.mapFromList(fakeGithubOwnerCacheList)
        assertEquals(fakeGithubOwnerDomainList, result)
    }
}