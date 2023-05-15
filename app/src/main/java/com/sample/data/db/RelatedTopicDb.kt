package com.sample.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.domain.domain_models.RelatedTopicModel

@Entity(tableName = "simpsons_table")
data class RelatedTopicDb(
    val firstURL: String ,
    val icon: String ,
    val result: String ,
    @PrimaryKey(autoGenerate = false)
    val text: String,
    @ColumnInfo val name: String

)

fun RelatedTopicDb.asDomain(): RelatedTopicModel {
    return RelatedTopicModel(
        firstURL = firstURL,
        icon = icon,
        result = result,
        text = text,
        name = name
    )
}
