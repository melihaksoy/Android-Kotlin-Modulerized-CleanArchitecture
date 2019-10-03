package com.melih.repository.entities

import androidx.room.ColumnInfo
import com.melih.abstractions.data.DataEntity
import com.melih.repository.DEFAULT_NAME
import com.melih.repository.EMPTY_STRING
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MissionEntity(
    @ColumnInfo(name = "id_mission") val id: Long = 0L,
    @ColumnInfo(name = "name_mission") val name: String = DEFAULT_NAME,
    val description: String = EMPTY_STRING,
    val typeName: String = EMPTY_STRING
) : DataEntity
