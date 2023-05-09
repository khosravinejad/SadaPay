package com.morteza.sadapay.presentation.mapper

import com.morteza.sadapay.presentation.utils.FakeData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GithubRepoOwnerDomainToPresentationMapperTest {

    private lateinit var sut: GithubRepoOwnerDomainPresentationMapper

    @Before
    fun setup() {
        sut = GithubRepoOwnerDomainPresentationMapper()
    }

    @Test
    fun `mapFrom should map given ownerDomainModel to expected ownerPresentationModel`() {
        val fakeGithubOwnerDomain = FakeData.fakeGithubReposDomainModel()[0].owner
        val expectedGithubOwnerPresentation = FakeData.fakeGithubReposPresentationModel()[0].owner

        val result = sut.mapToPresentation(fakeGithubOwnerDomain)
        assertEquals(expectedGithubOwnerPresentation, result)
    }

    @Test
    fun `mapFrom should map given ownerPresentationModel to expected ownerDomainModel`() {
        val fakeGithubOwnerPresentation = FakeData.fakeGithubReposPresentationModel()[0].owner
        val expectedGithubOwnerDomain = FakeData.fakeGithubReposDomainModel()[0].owner

        val result = sut.mapToDomain(fakeGithubOwnerPresentation)
        assertEquals(expectedGithubOwnerDomain, result)
    }
}