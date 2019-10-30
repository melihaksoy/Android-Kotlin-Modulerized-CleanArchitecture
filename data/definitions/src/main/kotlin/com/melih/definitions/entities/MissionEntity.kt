package com.melih.definitions.entities

import androidx.room.ColumnInfo
import com.melih.definitions.DEFAULT_NAME
import com.melih.definitions.EMPTY_STRING
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MissionEntity(
    @ColumnInfo(name = "id_mission") val id: Long = 0L,
    @ColumnInfo(name = "name_mission") val name: String = DEFAULT_NAME,
    val description: String = EMPTY_STRING,
    val typeName: String = EMPTY_STRING
)
