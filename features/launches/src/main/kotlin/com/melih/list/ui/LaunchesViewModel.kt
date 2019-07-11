package com.melih.list.ui

import com.melih.core.base.viewmodel.BaseViewModel
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.GetLaunches
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LaunchesViewModel @Inject constructor(
    private val getLaunches: GetLaunches,
    private val getLaunchesParams: GetLaunches.Params
) : BaseViewModel<List<LaunchEntity>>() {

    // region Functions

    /**
     * Triggering interactor in view model scope
     */
    override suspend fun loadData() {
        getLaunches(getLaunchesParams).collect {
            it.handle(::handleState, ::handleFailure, ::handleSuccess)
        }
    }
    // endregion
}
