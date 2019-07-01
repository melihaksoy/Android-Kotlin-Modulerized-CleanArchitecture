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
class GetLaunchDetails @Inject constructor(
    private val sourceManager: SourceManager
) : BaseInteractor<LaunchEntity, GetLaunchDetails.Params>() {

    @ExperimentalCoroutinesApi
    override suspend fun run(collector: FlowCollector<Result<LaunchEntity>>, params: Params) {
        collector.emit(sourceManager.getLaunchById(params.id))
    }

    data class Params(
        val id: Long
    ) : InteractorParameters
}
