package com.sample.simpsonsviewer.domain.domain_models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sample.simpsonsviewer.data.db.RelatedTopicDb
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