package com.melih.repository.sources

import com.melih.repository.Repository
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.Result
import com.melih.repository.persistence.LaunchesDatabase
import javax.inject.Inject

/**
 * Persistance source using Room database to save / read objects for SST - offline usage
 */
internal class PersistenceSource @Inject constructor(
    private val launchesDatabase: LaunchesDatabase
) : Repository() {
    // region Functions

    override suspend fun getNextLaunches(count: Int): Result<List<LaunchEntity>> =
        launchesDatabase
            .launchesDao
            .getLaunches(count)
            .takeIf { it.isNotEmpty() }
            ?.run {
                Result.Success(this)
            } ?: Result.Failure(Reason.PersistenceEmpty())

    override suspend fun getLaunchById(id: Long): Result<LaunchEntity> =
        launchesDatabase
            .launchesDao
            .getLaunchById(id)
            .takeIf { it != null }
            ?.run {
                Result.Success(this)
            } ?: Result.Failure(Reason.PersistenceEmpty())

    internal suspend fun saveLaunches(launches: List<LaunchEntity>) {
        launchesDatabase.launchesDao.updateLaunches(launches)
    }

    internal suspend fun saveLaunch(launch: LaunchEntity) {
        launchesDatabase.launchesDao.saveLaunch(launch)
    }
    // endregion
}
