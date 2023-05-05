package com.sample.simpsonsviewer.domain.domain_models


import com.google.gson.annotations.SerializedName
import com.sample.simpsonsviewer.data.db.RelatedTopicDb

data class RelatedTopicModel(
    val firstURL: String = "",
    val icon: IconModel = IconModel(),
    val result: String = "",
    val text: String = ""
)

fun RelatedTopicModel.asDb(): RelatedTopicDb {
    return RelatedTopicDb(
        firstURL = firstURL,
        icon = icon.urL,
        result = result,
        text = text
    )
}