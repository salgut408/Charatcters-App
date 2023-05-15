package com.sample.ui.main

import com.sample.domain.domain_models.RelatedTopicModel

data class ListUiState(
    val loading: Boolean = false,
    val currentList: List<RelatedTopicModel> = listOf(),
)
