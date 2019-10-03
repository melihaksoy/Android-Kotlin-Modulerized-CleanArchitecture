package com.melih.repository.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    //region Companion

    companion object {

        private lateinit var instance: LaunchesDatabase

        fun getInstance(ctx: Context): LaunchesDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(ctx, LaunchesDatabase::class.java, DB_NAME)
                    .build()
            }

            return instance
        }

    }
    //endregion

    //region Abstractions

    abstract val launchesDao: LaunchesDao
    //endregion
}
