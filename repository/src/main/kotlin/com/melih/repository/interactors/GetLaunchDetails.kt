package com.melih.repository.interactors

import com.melih.abstractions.deliverable.Failure
import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.deliverable.Success
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.BaseInteractor
import com.melih.repository.interactors.base.InteractorParameters
import com.melih.repository.sources.LaunchesSource
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Gets next given number of launches
 */
class GetLaunchDetails @Inject constructor() : BaseInteractor<LaunchEntity, GetLaunchDetails.Params>() {

    //region Properties

    @field:Inject
    internal lateinit var launchesSource: LaunchesSource

    //endregion

    //region Functions

    override suspend fun FlowCollector<Result<LaunchEntity>>.run(params: Params) {
        val result = launchesSource.getLaunchById(params.id)

        if (result !is Success) {
            when (val response = launchesSource.getLaunchById(params.id)) {
                // Save result and return again from persistence
                is Success -> {
                    emit(launchesSource.getLaunchById(params.id))
                }

                // Redirect failure as it is
                is Failure -> emit(response)
            }
        } else {
            emit(result)
        }
    }
    //endregion

    data class Params(
        val id: Long
    ) : InteractorParameters
}
