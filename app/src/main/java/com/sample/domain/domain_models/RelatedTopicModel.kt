package com.sample.domain.domain_models


import com.sample.data.db.RelatedTopicDb
import java.io.Serializable

data class RelatedTopicModel(
    val firstURL: String = "",
    val icon: String = "",
    val result: String = "",
    val text: String = "",
    val name: String = ""
): Serializable

fun RelatedTopicModel.asDb(): RelatedTopicDb {
    return RelatedTopicDb(
        firstURL = firstURL,
        icon = icon,
        result = result,
        text = text,
        name = text.split("-", ",").first()

    )
}