package com.melih.repository.persistence.converters

import com.melih.repository.entities.LocationEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Converts [location][LocationEntity]
 */
class LocationConverter : BaseConverter<LocationEntity>() {
    override fun getAdapter(moshi: Moshi): JsonAdapter<LocationEntity> =
        moshi.adapter(LocationEntity::class.java)
}
