package com.morteza.sadapay.data.source.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.morteza.sadapay.data.source.local.AppDatabase
import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import com.morteza.sadapay.data.source.local.model.GithubRepoOwnerCache
import com.morteza.sadapay.data.utils.SystemTimestampGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class GithubRepoDaoAndroidTest {

    // Google recommended to run pure Room tests as Android Test

    private lateinit var database: AppDatabase
    private lateinit var githubRepoDao: GithubRepoDao
    private lateinit var timestampGenerator: SystemTimestampGenerator


    @Before
    fun setup() {
        timestampGenerator = SystemTimestampGenerator()
        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
                .build()
        githubRepoDao = database.githubRepoDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertReposAndCollectItFromDb() = runTest {
        val expected = listOf(
            GithubRepoCache(
                id = 1,
                name = "repo name",
                fullName = "repo fullName",
                description = "repo description",
                language = "repo language",
                starsCount = 2354,
                owner = GithubRepoOwnerCache(
                    ownerId = 10, ownerName = "owner name", avatarUrl = "url"
                ),
                lastUpdated = timestampGenerator.generateTimestamp()
            ), GithubRepoCache(
                id = 2,
                name = "repo name 2",
                fullName = "repo fullName 2",
                description = "repo description 2",
                language = "repo language 2",
                starsCount = 3456,
                owner = GithubRepoOwnerCache(
                    ownerId = 20, ownerName = "owner name 2", avatarUrl = "url 2"
                ),
                lastUpdated = timestampGenerator.generateTimestamp()
            )
        )

        githubRepoDao.insertRepositories(expected)

        val actual = githubRepoDao.getRepositories().first()
        assertEquals(expected, actual)
    }
}