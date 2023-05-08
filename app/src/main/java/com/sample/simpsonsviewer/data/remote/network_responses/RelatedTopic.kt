package com.sample.simpsonsviewer.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.sample.simpsonsviewer.domain.domain_models.IconModel
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstURL: String? = "",
    @SerializedName("Icon")
    val icon: Icon? = Icon(),
    @SerializedName("Result")
    val result: String? = "",
    @SerializedName("Text")
    val text: String? = ""
)

fun RelatedTopic.asDomain(): RelatedTopicModel {
    return RelatedTopicModel(
        firstURL = firstURL ?: "",
        icon = icon?.urL ?: "",
        result = result ?: "",
        text = text ?: ""
    )
}