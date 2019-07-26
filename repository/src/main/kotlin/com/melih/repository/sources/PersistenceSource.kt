package com.melih.repository.sources

import android.content.Context
import com.melih.repository.Repository
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Failure
import com.melih.repository.interactors.base.PersistenceEmpty
import com.melih.repository.interactors.base.Result
import com.melih.repository.interactors.base.Success
import com.melih.repository.persistence.LaunchesDatabase
import javax.inject.Inject

/**
 * Persistance source using Room database to save / read objects for SST - offline usage
 */
internal class PersistenceSource @Inject constructor(
    ctx: Context
) : Repository() {
    // region Functions

    private val launchesDatabase = LaunchesDatabase.getInstance(ctx)

    override suspend fun getNextLaunches(count: Int, page: Int): Result<List<LaunchEntity>> =
        launchesDatabase
            .launchesDao
            .getLaunches(count, page)
            .takeIf { it.isNotEmpty() }
            ?.run {
                Success(this)
            } ?: Failure(PersistenceEmpty())

    override suspend fun getLaunchById(id: Long): Result<LaunchEntity> =
        launchesDatabase
            .launchesDao
            .getLaunchById(id)
            .takeIf { it != null }
            ?.run {
                Success(this)
            } ?: Failure(PersistenceEmpty())

    internal suspend fun saveLaunches(launches: List<LaunchEntity>) {
        launchesDatabase.launchesDao.saveLaunches(launches)
    }

    internal suspend fun saveLaunch(launch: LaunchEntity) {
        launchesDatabase.launchesDao.saveLaunch(launch)
    }
    // endregion
}
