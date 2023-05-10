package com.morteza.sadapay.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morteza.sadapay.data.source.local.dao.GithubRepoDao
import com.morteza.sadapay.data.source.local.model.GithubRepoCache

@Database(
    entities = [
        GithubRepoCache::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "githubrepo_android.db"
    }

    abstract val githubRepoDao: GithubRepoDao
}