package com.melih.repository

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Result

/**
 * Contract for sources to seperate low level business logic from build and return type
 */
abstract class Repository {

    //region Abstractions

    internal abstract suspend fun getNextLaunches(count: Int, page: Int): Result<List<LaunchEntity>>
    internal abstract suspend fun getLaunchById(id: Long): Result<LaunchEntity>
    //endregion
}
