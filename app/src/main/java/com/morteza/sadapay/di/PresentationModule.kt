package com.morteza.sadapay.di

import com.morteza.sadapay.presentation.mapper.GithubRepoDomainPresentationMapper
import com.morteza.sadapay.presentation.mapper.GithubRepoOwnerDomainPresentationMapper
import com.morteza.sadapay.presentation.utils.CoroutineContextProvider
import com.morteza.sadapay.presentation.utils.CoroutineContextProviderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideCoroutineContextProvider(contextProvider: CoroutineContextProviderImp): CoroutineContextProvider =
        contextProvider

    @Provides
    fun provideGithubOwnerRepoDomainPresentationMapper() = GithubRepoOwnerDomainPresentationMapper()

    @Provides
    fun provideGithubRepoOwnerDomainPresentationMapper(ownerMapper: GithubRepoOwnerDomainPresentationMapper) =
        GithubRepoDomainPresentationMapper(ownerMapper)
}