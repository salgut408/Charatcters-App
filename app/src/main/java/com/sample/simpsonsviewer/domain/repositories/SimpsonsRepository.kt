package com.sample.simpsonsviewer.domain.repositories

import androidx.lifecycle.LiveData
import com.sample.simpsonsviewer.data.db.RelatedTopicDb
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel
import kotlinx.coroutines.flow.Flow

interface SimpsonsRepository {
    suspend fun saveInDatabase()
    suspend fun getCharactersFromDb(): List<RelatedTopicModel>
    suspend fun searchDb(searchQuery: String): List<RelatedTopicModel>


}
