package com.melih.definitions.entities

import androidx.room.ColumnInfo
import com.melih.definitions.DEFAULT_NAME
import com.melih.definitions.EMPTY_STRING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketEntity(
    @ColumnInfo(name = "id_rocket") val id: Long = 0L,
    @ColumnInfo(name = "name_rocket") val name: String = DEFAULT_NAME,
    @field:Json(name = "familyname") val familyName: String = DEFAULT_NAME,
    val imageSizes: IntArray = intArrayOf(),
    val imageURL: String = EMPTY_STRING
)
