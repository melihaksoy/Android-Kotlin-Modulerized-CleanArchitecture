package com.melih.launches.data

import com.melih.abstractions.data.ViewEntity

data class LaunchItem(
    val id: Long,
    val imageUrl: String,
    val launchName: String,
    val launchDescription: String
) : ViewEntity
