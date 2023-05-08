package com.sample.simpsonsviewer.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RelatedTopicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<RelatedTopicDb>): List<Long>

    @Query("SELECT * FROM simpsons_table")
    fun getAllSavedItems(): LiveData<List<RelatedTopicModel>>
}