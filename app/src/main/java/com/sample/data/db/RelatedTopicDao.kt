package com.sample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.domain.domain_models.RelatedTopicModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RelatedTopicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<RelatedTopicDb>): List<Long>

    @Query("SELECT * FROM simpsons_table")
    fun getAllSavedItemsFlow(): Flow<List<RelatedTopicModel>>

    @Query("SELECT * FROM simpsons_table WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchDbFlow(searchQuery: String): Flow<List<RelatedTopicModel>>
}