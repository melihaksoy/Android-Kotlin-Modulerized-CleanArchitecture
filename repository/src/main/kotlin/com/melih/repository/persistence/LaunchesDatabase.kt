package com.melih.repository.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.persistence.converters.LocationConverter
import com.melih.repository.persistence.converters.MissionConverter
import com.melih.repository.persistence.converters.RocketConverter
import com.melih.repository.persistence.dao.LaunchesDao

const val DB_NAME = "LaunchesDB"

/**
 * DB that manages launches
 */
@Database(
    entities = [LaunchEntity::class],
    exportSchema = true,
    version = 1
)
@TypeConverters(
    LocationConverter::class,
    RocketConverter::class,
    MissionConverter::class
)
abstract class LaunchesDatabase : RoomDatabase() {

    abstract val launchesDao: LaunchesDao
}
