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

internal const val DEFAULT_LAUNCH_COUNT = 15

/**
 * Gets next given number of launches
 */
class GetLaunches @Inject constructor() : BaseInteractor<List<LaunchEntity>, GetLaunches.Params>() {

    @field:Inject
    internal lateinit var networkSource: NetworkSource

    @field:Inject
    internal lateinit var persistenceSource: PersistenceSource

    @ExperimentalCoroutinesApi
    override suspend fun run(collector: FlowCollector<Result<List<LaunchEntity>>>, params: Params) {

        // First return from persistence
        collector.emit(persistenceSource.getNextLaunches(params.count, params.page))

        // Start network fetch - note that we're not handling state here to ommit them
        when (val result = networkSource.getNextLaunches(params.count, params.page)) {

            // Save result and return again from persistence
            is Result.Success -> {
                persistenceSource.saveLaunches(result.successData)
                collector.emit(persistenceSource.getNextLaunches(params.count, params.page))
            }

            // Redirect failure as it is
            is Result.Failure -> collector.emit(result)
        }
    }

    data class Params(
        val count: Int = DEFAULT_LAUNCH_COUNT,
        val page: Int
    ) : InteractorParameters
}
