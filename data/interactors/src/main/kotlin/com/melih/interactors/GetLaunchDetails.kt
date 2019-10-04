package com.melih.repository.interactors

import com.melih.abstractions.data.ViewEntity
import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.mapper.Mapper
import com.melih.interactors.sources.LaunchesSource
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.BaseInteractor
import com.melih.repository.interactors.base.InteractorParameters
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Gets next given number of launches
 */
class GetLaunchDetails<T : ViewEntity> @Inject constructor(
    private val mapper: @JvmSuppressWildcards Mapper<LaunchEntity, T>
) : BaseInteractor<T, GetLaunchDetails.Params>() {

    //region Properties

    @field:Inject
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
