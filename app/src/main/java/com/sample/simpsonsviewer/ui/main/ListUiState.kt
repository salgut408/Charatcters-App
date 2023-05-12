package com.sample.simpsonsviewer.ui.main

import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel

data class ListUiState(
    val loading: Boolean = false,
    val currentList: List<RelatedTopicModel> = listOf(),
)
