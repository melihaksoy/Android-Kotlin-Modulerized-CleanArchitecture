package com.melih.repository.entities

import androidx.room.ColumnInfo
import com.melih.repository.DEFAULT_NAME
import com.melih.repository.EMPTY_STRING
import com.squareup.moshi.Json

data class RocketEntity(
    @ColumnInfo(name = "id_rocket") val id: Long = 0L,
    @ColumnInfo(name = "name_rocket") val name: String = DEFAULT_NAME,
    @field:Json(name = "familyname") val familyName: String = DEFAULT_NAME,
    val imageSizes: IntArray = intArrayOf(),
    val imageURL: String = EMPTY_STRING
)
