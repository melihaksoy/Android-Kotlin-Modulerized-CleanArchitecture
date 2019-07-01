package com.melih.repository

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Result

/**
 * Abstract class to create contract in sources to seperate low level business logic from build and return type
 */
abstract class Repository {

    internal abstract suspend fun getNextLaunches(count: Int): Result<List<LaunchEntity>>
    internal abstract suspend fun getLaunchById(id: Long): Result<LaunchEntity>
}
