package com.melih.repository.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchesEntity(
    val id: Long = 0L,
    val launches: List<LaunchEntity> = listOf(),
    val total: Int = 0,
    val offset: Int = 0,
    val count: Int = 0
)
