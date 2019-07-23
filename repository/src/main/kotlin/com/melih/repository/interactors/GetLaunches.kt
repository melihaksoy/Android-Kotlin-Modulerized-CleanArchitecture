@file:UseExperimental(ExperimentalCoroutinesApi::class)

package com.melih.repository.interactors

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.BaseInteractor
import com.melih.repository.interactors.base.InteractorParameters
import com.melih.repository.interactors.base.Result
import com.melih.repository.interactors.base.Success
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

    override suspend fun FlowCollector<Result<List<LaunchEntity>>>.run(params: Params) {

        // First return from persistence, ignoring errors
        persistenceSource.getNextLaunches(params.count, params.page)
            .takeIf { it is Success }
            ?.also {
                emit(it)
            }

        // Start network fetch - note that we're not handling state here to ommit them
        networkSource.getNextLaunches(params.count, params.page)
            .also {
                val data = if (it is Success) {
                    persistenceSource.saveLaunches(it.successData)
                    persistenceSource.getNextLaunches(params.count, params.page)
                } else {
                    it
                }

                emit(data)
            }
    }

    data class Params(
        val count: Int = DEFAULT_LAUNCH_COUNT,
        val page: Int
    ) : InteractorParameters
}
