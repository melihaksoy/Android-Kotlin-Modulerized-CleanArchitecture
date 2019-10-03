package com.melih.definitions

import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.mapper.Mapper
import com.melih.repository.entities.LaunchEntity

/**
 * Contract for sources to seperate low level business logic from build and return type
 */
interface Repository {

    //region Abstractions

    suspend fun <T> getNextLaunches(
        count: Int,
        page: Int,
        mapper: Mapper<LaunchEntity, T>
    ): Result<List<T>>

    suspend fun <T> getLaunchById(id: Long, mapper: Mapper<LaunchEntity, T>): Result<T>
    //endregion
}
