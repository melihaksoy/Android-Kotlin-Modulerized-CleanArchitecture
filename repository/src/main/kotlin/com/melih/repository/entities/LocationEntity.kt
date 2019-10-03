package com.melih.repository.entities

import androidx.room.ColumnInfo
import com.melih.repository.DEFAULT_NAME
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationEntity(
    @ColumnInfo(name = "id_location") val id: Long = 0L,
    @ColumnInfo(name = "name_location") val name: String = DEFAULT_NAME,
    val pads: List<PadEntity> = listOf(PadEntity())
)

@JsonClass(generateAdapter = true)
data class PadEntity(
    @ColumnInfo(name = "id_pad") val id: Long = 0L,
    @ColumnInfo(name = "name_pad") val name: String = DEFAULT_NAME,
    val lat: Long = 0L,
    val long: Long = 0L
)
