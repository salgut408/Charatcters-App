package com.sample.simpsonsviewer.domain.domain_models


import com.google.gson.annotations.SerializedName

data class SimpsonsModel(
    val abstract: String = "",
    val abstractSource: String = "",
    val abstractText: String = "",
    val abstractURL: String = "",
    val answer: String = "",
    val answerType: String = "",
    val definition: String = "",
    val definitionSource: String = "",
    val definitionURL: String = "",
    val entity: String = "",
    val heading: String = "",
    val image: String = "",
    val imageHeight: Int = 0,
    val imageIsLogo: Int = 0,
    val imageWidth: Int = 0,
    val infobox: String = "",
    val meta: MetaModel = MetaModel(),
    val redirect: String = "",
    val relatedTopics: List<RelatedTopicModel> = listOf(),
    val results: List<Any> = listOf(),
    val type: String = ""
)