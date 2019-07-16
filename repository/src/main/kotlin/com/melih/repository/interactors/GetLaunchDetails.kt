package com.melih.repository.interactors

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.BaseInteractor
import com.melih.repository.interactors.base.InteractorParameters
import com.melih.repository.interactors.base.Result
import com.melih.repository.sources.NetworkSource
import com.melih.repository.sources.PersistenceSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Gets next given number of launches
 */
class GetLaunchDetails @Inject constructor() : BaseInteractor<LaunchEntity, GetLaunchDetails.Params>() {

    @field:Inject
    internal lateinit var networkSource: NetworkSource

    @field:Inject
    internal lateinit var persistenceSource: PersistenceSource

    @ExperimentalCoroutinesApi
    override suspend fun run(collector: FlowCollector<Result<LaunchEntity>>, params: Params) {
        val result = persistenceSource.getLaunchById(params.id)

        if (result !is Result.Success) {
            when (val response = networkSource.getLaunchById(params.id)) {
                // Save result and return again from persistence
                is Result.Success -> {
                    persistenceSource.saveLaunch(response.successData)
                    collector.emit(persistenceSource.getLaunchById(params.id))
                }

                // Redirect failure as it is
                is Result.Failure -> collector.emit(response)
            }
        } else {
            collector.emit(result)
        }
    }

    data class Params(
        val id: Long
    ) : InteractorParameters
}
