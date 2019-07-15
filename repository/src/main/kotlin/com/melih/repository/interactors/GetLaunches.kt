package com.melih.repository.interactors

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.BaseInteractor
import com.melih.repository.interactors.base.InteractorParameters
import com.melih.repository.interactors.base.Result
import com.melih.repository.sources.SourceManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Gets next given number of launches
 */
class GetLaunches @Inject constructor() : BaseInteractor<List<LaunchEntity>, GetLaunches.Params>() {

    @field:Inject
    internal lateinit var sourceManager: SourceManager

    @ExperimentalCoroutinesApi
    override suspend fun run(collector: FlowCollector<Result<List<LaunchEntity>>>, params: Params) {
        collector.emit(sourceManager.getNextLaunches(params.count))
    }

    data class Params(
        val count: Int = 10
    ) : InteractorParameters
}
