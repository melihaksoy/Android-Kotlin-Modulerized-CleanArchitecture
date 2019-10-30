package com.melih.persistence.converters

import com.melih.definitions.entities.MissionEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Converts [mission][MissionEntity]
 */
class MissionConverter : BaseListConverter<MissionEntity>() {

    override fun getAdapter(moshi: Moshi): JsonAdapter<List<MissionEntity>> =
        moshi.adapter(
            Types.newParameterizedType(
                List::class.java,
                MissionEntity::class.java
            )
        )
}
