package com.melih.interactors

import com.melih.abstractions.data.ViewEntity
import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.mapper.Mapper
import com.melih.definitions.entities.LaunchEntity
import com.melih.interactors.base.BaseInteractor
import com.melih.interactors.base.InteractorParameters
import com.melih.interactors.sources.LaunchesSource
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Gets next given number of launches
 */
class GetLaunchDetails<T : ViewEntity> @Inject constructor(
    private val mapper: @JvmSuppressWildcards Mapper<LaunchEntity, T>
) : BaseInteractor<T, GetLaunchDetails.Params>() {

    //region Properties

    @Inject
    internal lateinit var launchesSource: LaunchesSource
    //endregion

    //region Functions

    override suspend fun FlowCollector<Result<T>>.run(params: Params) {
        emit(launchesSource.getLaunchById(params.id, mapper))
    }
    //endregion


    //region Parameters

    data class Params(
        val id: Long
    ) : InteractorParameters
    //endregion
}
