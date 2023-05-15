package com.sample.data.repository

import com.sample.data.db.RelatedTopicDao
import com.sample.data.remote.api_service.SimpsonsApi
import com.sample.data.remote.network_responses.asDomain
import com.sample.domain.domain_models.RelatedTopicModel
import com.sample.domain.domain_models.asDb
import com.sample.domain.repositories.SimpsonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SimpsonsRepositoryImpl @Inject constructor(
    val simpsonsApi: SimpsonsApi,
    val dao: RelatedTopicDao
): SimpsonsRepository {


    override suspend fun saveInDatabase() {
        withContext(Dispatchers.IO){
            val items = simpsonsApi.getCharacters().body()?.relatedTopics?.map { it?.asDomain()!!}!!
            dao.insert(items.map { it.asDb() })
        }
    }

    override suspend fun getCharactersFromDbFlow(): Flow<List<RelatedTopicModel>> {
        val result: Flow<List<RelatedTopicModel>>
        withContext(Dispatchers.IO){
            result = dao.getAllSavedItemsFlow()
        }
        return result
    }

    override suspend fun searchDbFlow(searchQuery: String): Flow<List<RelatedTopicModel>> {
        val result: Flow<List<RelatedTopicModel>>
        withContext(Dispatchers.IO) {
            result = dao.searchDbFlow(searchQuery)
        }
        return result
    }
}













