package com.melih.list.ui

import androidx.lifecycle.viewModelScope
import com.melih.core.base.viewmodel.BaseViewModel
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.GetLaunches
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LaunchesViewModel @Inject constructor(
    private val getLaunches: GetLaunches,
    private val getLaunchesParams: GetLaunches.Params
) : BaseViewModel<List<LaunchEntity>>() {

    // region Initialization

    init {
        loadData()
    }
    // endregion

    // region Functions

    /**
     * Triggering interactor in view model scope
     */
    override fun loadData() {
        viewModelScope.launch {
            getLaunches(getLaunchesParams).collect {
                it.handle(::handleState, ::handleFailure, ::handleSuccess)
            }
        }
    }
    // endregion
}
