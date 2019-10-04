package com.melih.persistence.converters

import com.melih.definitions.entities.LocationEntity
import com.melih.definitions.entities.LocationEntityJsonAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Converts [location][LocationEntity]
 */
class LocationConverter : BaseConverter<LocationEntity>() {

    override fun getAdapter(moshi: Moshi): JsonAdapter<LocationEntity> =
        LocationEntityJsonAdapter(moshi)
}
