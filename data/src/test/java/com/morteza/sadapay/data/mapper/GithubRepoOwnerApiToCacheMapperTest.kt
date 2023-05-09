package com.morteza.sadapay.data.mapper

import com.morteza.sadapay.data.utils.FakeData
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given

class GithubRepoOwnerApiToCacheMapperTest {
    private lateinit var sut: GithubRepoOwnerApiToCacheMapper

    @Before
    fun setup() {
        sut = GithubRepoOwnerApiToCacheMapper()
    }

    @Test
    fun `mapFrom should map given ownerApiModel to expected ownerCacheModel`() {
        val fakeGithubOwnerApi = FakeData.fakeGithubRepoApiModels()[0].owner
        val fakeGithubOwnerCache = FakeData.fakeCachedGithubRepos()[0].owner
        val result = sut.mapFrom(fakeGithubOwnerApi)
        Assert.assertEquals(fakeGithubOwnerCache, result)
    }

    @Test
    fun `mapFromList should map given ownerApiModels to expected ownerCacheModels`() {
        val fakeGithubOwnerApiList = FakeData.fakeGithubRepoApiModels(1).map { it.owner }
        val fakeGithubOwnerCacheList = FakeData.fakeCachedGithubRepos(1).map { it.owner }

        val result = sut.mapFromList(fakeGithubOwnerApiList)
        Assert.assertEquals(fakeGithubOwnerCacheList, result)
    }
}