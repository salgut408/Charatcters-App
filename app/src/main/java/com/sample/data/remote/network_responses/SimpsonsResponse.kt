package com.sample.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.sample.domain.domain_models.MetaModel
import com.sample.domain.domain_models.SimpsonsModel

data class SimpsonsResponse(
    @SerializedName("Abstract")
    val abstract: String? = "",
    @SerializedName("AbstractSource")
    val abstractSource: String? = "",
    @SerializedName("AbstractText")
    val abstractText: String? = "",
    @SerializedName("AbstractURL")
    val abstractURL: String? = "",
    @SerializedName("Answer")
    val answer: String? = "",
    @SerializedName("AnswerType")
    val answerType: String? = "",
    @SerializedName("Definition")
    val definition: String? = "",
    @SerializedName("DefinitionSource")
    val definitionSource: String? = "",
    @SerializedName("DefinitionURL")
    val definitionURL: String? = "",
    @SerializedName("Entity")
    val entity: String? = "",
    @SerializedName("Heading")
    val heading: String? = "",
    @SerializedName("Image")
    val image: String? = "",
    @SerializedName("ImageHeight")
    val imageHeight: Int? = 0,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int? = 0,
    @SerializedName("ImageWidth")
    val imageWidth: Int? = 0,
    @SerializedName("Infobox")
    val infobox: String? = "",
    @SerializedName("meta")
    val meta: Meta? = Meta(),
    @SerializedName("Redirect")
    val redirect: String? = "",
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic?> = listOf(),
    @SerializedName("Results")
    val results: List<Any?>? = listOf(),
    @SerializedName("Type")
    val type: String? = ""
)

fun SimpsonsResponse.asDomain(): SimpsonsModel {
    return SimpsonsModel(
        abstract = abstract ?: "",
        abstractSource = abstractSource ?: "",
        abstractText = abstractText ?: "",
        abstractURL = abstractURL ?: "",
        answer = answer ?: "",
        answerType = answerType ?: "",
        definition = definition ?: "",
        definitionSource = definitionSource ?: "",
        definitionURL = definitionURL ?: "",
        entity = entity ?: "",
        heading = heading ?: "",
        image = image ?: "",
        imageHeight = imageHeight ?: 0,
        imageIsLogo = imageIsLogo ?: 0,
        imageWidth = imageWidth ?: 0,
        infobox = infobox ?: "",
        meta = meta?.asDomain() ?: MetaModel(),
        redirect = redirect ?: "",
        relatedTopics = relatedTopics.map { it?.asDomain()!!  }
    )
}

