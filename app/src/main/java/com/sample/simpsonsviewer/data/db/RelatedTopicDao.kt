package com.sample.simpsonsviewer.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RelatedTopicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<RelatedTopicDb>): List<Long>

    @Query("SELECT * FROM simpsons_table")
    fun getAllSavedItems(): Flow<List<RelatedTopicDb>>
}