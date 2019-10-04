package com.melih.persistence.converters

import com.melih.definitions.entities.RocketEntity
import com.melih.definitions.entities.RocketEntityJsonAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Converts [rocket][RocketEntity]
 */
class RocketConverter : BaseConverter<RocketEntity>() {

    override fun getAdapter(moshi: Moshi): JsonAdapter<RocketEntity> =
        RocketEntityJsonAdapter(moshi)
}
