package com.melih.definitions

import com.melih.abstractions.data.ViewEntity
import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.mapper.Mapper
import com.melih.repository.entities.LaunchEntity

/**
 * Contract for sources to seperate business logic from build and return type
 */
interface Source {

    //region Abstractions

    suspend fun <T : ViewEntity> getNextLaunches(
        count: Int,
        page: Int,
        mapper: Mapper<LaunchEntity, T>
    ): Result<List<T>>

    suspend fun <T : ViewEntity> getLaunchById(id: Long, mapper: Mapper<LaunchEntity, T>): Result<T>
    //endregion
}
