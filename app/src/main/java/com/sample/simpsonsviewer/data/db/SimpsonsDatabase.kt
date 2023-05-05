package com.sample.simpsonsviewer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RelatedTopicDb::class,],
    version = 1,
    exportSchema = false
)

abstract class SimpsonsDatabase(): RoomDatabase() {

    abstract fun getDao(): RelatedTopicDao
}