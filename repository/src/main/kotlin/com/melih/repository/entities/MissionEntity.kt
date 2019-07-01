package com.melih.repository.entities

import androidx.room.ColumnInfo
import com.melih.repository.DEFAULT_NAME
import com.melih.repository.EMPTY_STRING

data class MissionEntity(
    @ColumnInfo(name = "id_mission") val id: Long = 0L,
    @ColumnInfo(name = "name_mission") val name: String = DEFAULT_NAME,
    val description: String = EMPTY_STRING,
    val typeName: String = EMPTY_STRING
)
