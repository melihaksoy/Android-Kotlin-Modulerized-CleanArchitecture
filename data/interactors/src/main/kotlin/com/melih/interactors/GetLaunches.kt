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

const val DEFAULT_LAUNCHES_AMOUNT = 15

/**
 * Gets next given number of launches
 */
class GetLaunches<T : ViewEntity> @Inject constructor(
    private val mapper: @JvmSuppressWildcards Mapper<LaunchEntity, T>
) : BaseInteractor<List<T>, GetLaunches.Params>() {

    //region Properties

    @field:Inject
    internal lateinit var launchesSource: LaunchesSource
    //endregion

    //region Functions

    override suspend fun FlowCollector<Result<List<T>>>.run(params: Params) {

        // Start network fetch - we're not handling state here to ommit them
        emit(
            launchesSource
                .getNextLaunches(params.count, params.page, mapper)
        )
    }
    //endregion

    data class Params(
        val count: Int = DEFAULT_LAUNCHES_AMOUNT,
        val page: Int
    ) : InteractorParameters
}
