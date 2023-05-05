package com.sample.simpsonsviewer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "simpsons_table")
data class RelatedTopicDb(
    val firstURL: String ,
    val icon: String ,
    val result: String ,
    @PrimaryKey(autoGenerate = false)
    val text: String

)
