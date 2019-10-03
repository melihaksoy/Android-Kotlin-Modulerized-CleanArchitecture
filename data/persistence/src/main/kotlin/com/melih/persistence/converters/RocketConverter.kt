package com.melih.repository.persistence.converters

import com.melih.repository.entities.RocketEntity
import com.melih.repository.entities.RocketEntityJsonAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Converts [rocket][RocketEntity]
 */
class RocketConverter : BaseConverter<RocketEntity>() {

    override fun getAdapter(moshi: Moshi): JsonAdapter<RocketEntity> =
        RocketEntityJsonAdapter(moshi)
}
