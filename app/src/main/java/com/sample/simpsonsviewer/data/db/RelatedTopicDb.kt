package com.sample.simpsonsviewer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.simpsonsviewer.domain.domain_models.IconModel
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel

@Entity(tableName = "simpsons_table")
data class RelatedTopicDb(
    val firstURL: String ,
    val icon: String ,
    val result: String ,
    @PrimaryKey(autoGenerate = false)
    val text: String

)

fun RelatedTopicDb.asDomain(): RelatedTopicModel {
    return RelatedTopicModel(
        firstURL = firstURL,
        icon = icon,
        result = result,
        text = text
    )
}
