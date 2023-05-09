package com.morteza.sadapay.data.source.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class GithubRepoCache(
    @PrimaryKey val id: Int,
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
    val starsCount: Int,
    @Embedded
    // I made it Embedded for simplicity.
    // The better approach in point of scalability is to create Owner entity
    // and put ownerId as a foreign key here
    val owner: GithubRepoOwnerCache,
    val lastUpdated: Long // timestamp for last updated time
)
