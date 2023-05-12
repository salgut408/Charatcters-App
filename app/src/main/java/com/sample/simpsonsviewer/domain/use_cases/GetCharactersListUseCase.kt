package com.sample.simpsonsviewer.domain.use_cases

import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val characterRepository: SimpsonsRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(): Flow<List<RelatedTopicModel>> =
        withContext(defaultDispatcher){
            characterRepository.saveInDatabase()
            return@withContext characterRepository.getCharactersFromDbFlow()

        }
}