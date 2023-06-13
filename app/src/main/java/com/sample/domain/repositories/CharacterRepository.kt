package com.sample.domain.repositories

import com.sample.domain.domain_models.RelatedTopicModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun saveInDatabase()
    suspend fun getCharactersFromDbFlow(): Flow<List<RelatedTopicModel>>
    suspend fun searchDbFlow(searchQuery: String): Flow<List<RelatedTopicModel>>
    suspend fun getTitleBarString(): String


}
