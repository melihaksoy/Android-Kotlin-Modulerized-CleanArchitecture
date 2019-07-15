package com.melih.repository.sources

import com.melih.repository.Repository
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.Result
import javax.inject.Inject

/**
 * Manages SST by using network & persistance sources
 */
internal class SourceManager @Inject constructor(
    private val networkSource: NetworkSource,
    private val persistenceSource: PersistenceSource
) : Repository() {

    // region Functions

    override suspend fun getNextLaunches(count: Int): Result<List<LaunchEntity>> {
        networkSource
            .getNextLaunches(count)
            .takeIf { it is Result.Success }
            ?.let {
                persistenceSource.saveLaunches((it as Result.Success).successData)
            }

        return persistenceSource
            .getNextLaunches(count)
            .takeIf {
                it is Result.Success && it.successData.isNotEmpty()
            }
            ?: Result.Failure(Reason.NoNetworkPersistenceEmpty())
    }

    override suspend fun getLaunchById(id: Long): Result<LaunchEntity> {
        val result =
            persistenceSource
                .getLaunchById(id)

        return if (result is Result.Failure) {
            networkSource
                .getLaunchById(id)
                .takeIf { it is Result.Success }
                ?.let {
                    persistenceSource.saveLaunch((it as Result.Success).successData)
                }

            persistenceSource
                .getLaunchById(id)
        } else {
            result
        }
    }
    // endregion
}
