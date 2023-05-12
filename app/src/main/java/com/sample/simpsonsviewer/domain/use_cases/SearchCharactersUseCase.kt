package com.sample.simpsonsviewer.domain.use_cases

import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import com.sample.simpsonsviewer.domain.repositories.SimpsonsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val characterRepository: SimpsonsRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(searchQuery: String): List<RelatedTopicModel> =
        withContext(defaultDispatcher) {
            return@withContext characterRepository.searchDb(searchQuery)
        }
}