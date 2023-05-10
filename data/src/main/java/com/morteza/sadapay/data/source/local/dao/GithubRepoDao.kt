package com.morteza.sadapay.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.morteza.sadapay.data.source.local.model.GithubRepoCache
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepoDao {

    @Query("SELECT * FROM repository")
    fun getRepositories(): Flow<List<GithubRepoCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<GithubRepoCache>)

    @Query("DELETE FROM repository")
    suspend fun clearTable()

    @Query("SELECT lastUpdated FROM repository ORDER BY lastUpdated DESC LIMIT 1")
    suspend fun getLastUpdatedTimestamp(): Long?
}