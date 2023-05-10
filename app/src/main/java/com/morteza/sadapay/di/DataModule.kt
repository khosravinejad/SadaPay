package com.morteza.sadapay.di

import android.app.Application
import androidx.room.Room
import com.morteza.sadapay.data.mapper.GithubRepoApiToCacheMapper
import com.morteza.sadapay.data.mapper.GithubRepoCacheToDomainMapper
import com.morteza.sadapay.data.mapper.GithubRepoOwnerApiToCacheMapper
import com.morteza.sadapay.data.mapper.GithubRepoOwnerCacheToDomain
import com.morteza.sadapay.data.repository.TrendingRepositoryImpl
import com.morteza.sadapay.data.source.local.AppDatabase
import com.morteza.sadapay.data.source.remote.RepoApiServices
import com.morteza.sadapay.data.utils.SystemTimestampGenerator
import com.morteza.sadapay.domain.repository.TrendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppDatabase.DB_NAME
    }

    @Provides
    fun provideDatabase(@DatabaseInfo dbName: String, application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Provides
    fun provideTimestampGenerator(): SystemTimestampGenerator = SystemTimestampGenerator()

    @Provides
    fun provideGithubOwnerRepoApiToCacheMapper() = GithubRepoOwnerApiToCacheMapper()

    @Provides
    fun provideGithubRepoApiToCacheMapper(
        ownerMapper: GithubRepoOwnerApiToCacheMapper,
        timestampGenerator: SystemTimestampGenerator
    ) =
        GithubRepoApiToCacheMapper(ownerMapper, timestampGenerator)

    @Provides
    fun provideGithubOwnerRepoCacheToDomainMapper() = GithubRepoOwnerCacheToDomain()

    @Provides
    fun provideGithubRepoCacheToDomainMapper(ownerMapper: GithubRepoOwnerCacheToDomain) =
        GithubRepoCacheToDomainMapper(ownerMapper)

    @Provides
    fun provideTrendingRepository(
        apiServices: RepoApiServices,
        database: AppDatabase,
        cacheToDomainMapper: GithubRepoCacheToDomainMapper,
        apiToCacheMapper: GithubRepoApiToCacheMapper,
        timestampGenerator: SystemTimestampGenerator
    ): TrendingRepository = TrendingRepositoryImpl(
        apiServices = apiServices,
        githubRepoDao = database.githubRepoDao,
        cacheToDomainMapper = cacheToDomainMapper,
        apiToCacheMapper = apiToCacheMapper,
        timestampGenerator = timestampGenerator
    )
}