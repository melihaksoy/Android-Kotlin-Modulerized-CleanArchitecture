package com.melih.launches.data

import com.melih.abstractions.mapper.Mapper
import com.melih.definitions.entities.LaunchEntity
import javax.inject.Inject

class LaunchDetailMapper @Inject constructor() : Mapper<LaunchEntity, LaunchDetailItem>() {

    override fun convert(launchEntity: LaunchEntity) =
        with(launchEntity) {
            LaunchDetailItem(
                id,
                rocket.imageURL,
                rocket.name,
                if (!missions.isNullOrEmpty()) missions[0].description else ""
            )
        }
}
