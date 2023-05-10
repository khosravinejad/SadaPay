package com.morteza.sadapay.di

import com.google.gson.GsonBuilder
import com.morteza.sadapay.BuildConfig
import com.morteza.sadapay.data.source.remote.RepoApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {

        val okHttpBuilder = okHttpClient.newBuilder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .baseUrl(RepoApiServices.BASE_URL)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): RepoApiServices =
        retrofit.build().create(RepoApiServices::class.java)
}