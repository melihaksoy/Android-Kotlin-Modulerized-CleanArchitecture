package com.melih.definitions.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.melih.definitions.DEFAULT_NAME
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "Launches")
@JsonClass(generateAdapter = true)
data class LaunchEntity(
    @PrimaryKey val id: Long = 0L,
    val name: String = DEFAULT_NAME,
    @field:Json(name = "wsstamp") val launchStartTime: Long = 0L,
    @field:Json(name = "westamp") val launchEndTime: Long = 0L,
    val location: LocationEntity = LocationEntity(),
    val rocket: RocketEntity = RocketEntity(),
    val missions: List<MissionEntity> = listOf(MissionEntity())
)
