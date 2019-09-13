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

const val DEFAULT_LAUNCHES_AMOUNT = 15

/**
 * Gets next given number of launches
 */
@UseExperimental(ExperimentalCoroutinesApi::class)
class GetLaunches @Inject constructor() : BaseInteractor<List<LaunchEntity>, GetLaunches.Params>() {

    //region Properties

    @field:Inject
    internal lateinit var networkSource: NetworkSource

    @field:Inject
    internal lateinit var persistenceSource: PersistenceSource
    //endregion

    //region Functions

    override suspend fun FlowCollector<Result<List<LaunchEntity>>>.run(params: Params) {

        // Start network fetch - we're not handling state here to ommit them
        networkSource
            .getNextLaunches(params.count, params.page)
            .also {
                if (it is Success) {
                    persistenceSource.saveLaunches(it.successData)
                    emit(persistenceSource.getNextLaunches(params.count, params.page))
                } else {
                    emit(it)
                    emit(persistenceSource.getNextLaunches(params.count, params.page))
                }
            }
    }
    //endregion

    data class Params(
        val count: Int = DEFAULT_LAUNCHES_AMOUNT,
        val page: Int
    ) : InteractorParameters
}
