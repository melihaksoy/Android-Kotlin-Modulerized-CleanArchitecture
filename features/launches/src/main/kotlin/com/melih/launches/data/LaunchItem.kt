package com.melih.launches.data

import com.melih.abstractions.data.ViewEntity

data class LaunchItem(
    val id: Long,
    val imageUrl: String,
    val rocketName: String,
    val missionDescription: String
) : ViewEntity
