package com.sample.simpsonsviewer.domain.repositories

import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.domain_models.SimpsonsModel

interface SimpsonsRepository {
    suspend fun getSimpsonsList(): List<RelatedTopicModel>
    suspend fun getSimpsonsModel(): SimpsonsModel
    suspend fun saveInDatabase()

}
