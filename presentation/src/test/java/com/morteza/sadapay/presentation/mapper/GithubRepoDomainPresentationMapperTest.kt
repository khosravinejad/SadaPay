package com.morteza.sadapay.presentation.mapper

import com.morteza.sadapay.presentation.utils.FakeData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GithubRepoDomainPresentationMapperTest {

    private lateinit var githubRepoOwnerDomainToPresentationMapper: GithubRepoOwnerDomainPresentationMapper
    private lateinit var sut: GithubRepoDomainPresentationMapper

    @Before
    fun setup() {
        githubRepoOwnerDomainToPresentationMapper = mock()
        sut = GithubRepoDomainPresentationMapper(githubRepoOwnerDomainToPresentationMapper)
    }

    @Test
    fun `mapFrom should map given domainModel to expected presentationModel`() {
        val fakeDomainModel = FakeData.fakeGithubReposDomainModel()[0]
        val expectedPresentationModel = FakeData.fakeGithubReposPresentationModel()[0]

        given(githubRepoOwnerDomainToPresentationMapper.mapToPresentation(fakeDomainModel.owner)).willReturn(
            expectedPresentationModel.owner
        )

        val actual = sut.mapToPresentation(fakeDomainModel)
        assertEquals(expectedPresentationModel, actual)
    }

    @Test
    fun `mapFrom should map given presentationModel to expected domainModel`() {
        val fakePresentationModel = FakeData.fakeGithubReposPresentationModel()[0]
        val expectedDomainModel = FakeData.fakeGithubReposDomainModel()[0]

        given(githubRepoOwnerDomainToPresentationMapper.mapToDomain(fakePresentationModel.owner)).willReturn(
            expectedDomainModel.owner
        )

        val actual = sut.mapToDomain(fakePresentationModel)
        assertEquals(expectedDomainModel, actual)
    }
}