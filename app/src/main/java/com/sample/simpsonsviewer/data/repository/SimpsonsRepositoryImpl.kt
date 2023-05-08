package com.sample.simpsonsviewer.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.sample.simpsonsviewer.data.db.RelatedTopicDao
import com.sample.simpsonsviewer.data.db.SimpsonsDatabase
import com.sample.simpsonsviewer.data.remote.api_service.SimpsonsApi
import com.sample.simpsonsviewer.data.remote.network_responses.asDomain
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel
import com.sample.simpsonsviewer.domain.domain_models.asDb
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SimpsonsRepositoryImpl @Inject constructor(
    val simpsonsApi: SimpsonsApi,
    val simpsonsDatabase: SimpsonsDatabase,
    val dao: RelatedTopicDao
): SimpsonsRepository {
    override suspend fun getSimpsonsList(): List<RelatedTopicModel> {
        try {
//            val list = simpsonsApi.getCharacters().body()?.relatedTopics?.map { it?.asDomain() !!}
//            Log.e("GET_CHARS_REPO_LST", list.toString())
//            return list!!
        } catch (e: Exception){
            Log.e("GET_CHARS_REPOLST", e.message.toString())
        }
//        return simpsonsApi.getCharacters().body()?.relatedTopics?.map { it?.asDomain() !! }!!
        return listOf<RelatedTopicModel>()
    }

    override suspend fun getSimpsonsModel(): SimpsonsModel {
        try {
            val result = simpsonsApi.getCharacters().body()?.asDomain()!!
//            Log.e("GET_CHARS_REPO", result.toString())
            return result
        } catch (e: Exception){
            Log.e("GET_CHARS_REPO", e.message.toString())
        }
        val result = simpsonsApi.getCharacters().body()?.asDomain()!!
        return result
    }

    override suspend fun saveInDatabase() {
        withContext(Dispatchers.IO){
            val items = simpsonsApi.getCharacters().body()?.relatedTopics?.map { it?.asDomain() !!}!!
            dao.insert(items.map { it.asDb() })
        }
    }

    override suspend fun getCharactersFromDb(): List<RelatedTopicModel> {
        var result = listOf<RelatedTopicModel>()
        withContext(Dispatchers.IO){
             result = dao.getAllSavedItems()

        }
        return result

    }
//
//    override suspend fun getAllNamesDb(): List<String> {
//        var result = listOf<String>()
//        withContext(Dispatchers.IO){
//            result = dao.getAllSavedNames()
//        }
//        return result
//    }
}













