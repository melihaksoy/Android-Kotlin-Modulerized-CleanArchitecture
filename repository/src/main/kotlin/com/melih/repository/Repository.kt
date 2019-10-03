package com.melih.repository

import com.melih.abstractions.mapper.Mapper
import com.melih.abstractions.data.ViewEntity
import com.melih.repository.entities.LaunchEntity
import com.melih.abstractions.deliverable.Result

/**
 * Contract for sources to seperate low level business logic from build and return type
 */
abstract class Repository {

    //region Abstractions

    internal abstract suspend fun <T : ViewEntity> getNextLaunches(
        count: Int,
        page: Int,
        mapper: Mapper<LaunchEntity, T>
    ): Result<List<T>>

    internal abstract suspend fun getLaunchById(id: Long): Result<LaunchEntity>
    //endregion
}
