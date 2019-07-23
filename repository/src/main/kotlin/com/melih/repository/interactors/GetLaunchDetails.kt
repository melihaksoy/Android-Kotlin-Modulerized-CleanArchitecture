package com.melih.repository.interactors

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.BaseInteractor
import com.melih.repository.interactors.base.Failure
import com.melih.repository.interactors.base.InteractorParameters
import com.melih.repository.interactors.base.Result
import com.melih.repository.interactors.base.Success
import com.melih.repository.sources.NetworkSource
import com.melih.repository.sources.PersistenceSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Gets next given number of launches
 */
@UseExperimental(ExperimentalCoroutinesApi::class)
class GetLaunchDetails @Inject constructor() : BaseInteractor<LaunchEntity, GetLaunchDetails.Params>() {

    @field:Inject
    internal lateinit var networkSource: NetworkSource

    @field:Inject
    internal lateinit var persistenceSource: PersistenceSource

    override suspend fun FlowCollector<Result<LaunchEntity>>.run(params: Params) {
        val result = persistenceSource.getLaunchById(params.id)

        if (result !is Success) {
            when (val response = networkSource.getLaunchById(params.id)) {
                // Save result and return again from persistence
                is Success -> {
                    persistenceSource.saveLaunch(response.successData)
                    emit(persistenceSource.getLaunchById(params.id))
                }

                // Redirect failure as it is
                is Failure -> emit(response)
            }
        } else {
            emit(result)
        }
    }

    data class Params(
        val id: Long
    ) : InteractorParameters
}
