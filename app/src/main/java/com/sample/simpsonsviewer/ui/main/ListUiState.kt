package com.sample.simpsonsviewer.ui.main

import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import kotlinx.coroutines.flow.Flow

data class ListUiState(
    val loading: Boolean = false,
    val currentList: List<RelatedTopicModel>,
)
