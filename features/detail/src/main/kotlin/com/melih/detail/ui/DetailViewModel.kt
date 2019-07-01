package com.melih.detail.ui

import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.melih.core.base.viewmodel.BaseViewModel
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.GetLaunchDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailViewModel @Inject constructor(
    private val getLaunchDetails: GetLaunchDetails
) : BaseViewModel<LaunchEntity>() {

    // region Properties

    private var params = GetLaunchDetails.Params(INVALID_LAUNCH_ID)

    val rocketName = Transformations.map(successData) {
        it.rocket.name
    }

    val description = Transformations.map(successData) {
        if (it.missions.isEmpty()) {
            ""
        } else {
            it.missions[0].description
        }
    }

    val imageUrl = Transformations.map(successData) {
        it.rocket.imageURL
    }
    // endregion

    // region Functions

    fun createParamsFor(id: Long) {
        params = GetLaunchDetails.Params(id)
    }

    /**
     * Triggering interactor in view model scope
     */
    override fun loadData() {
        viewModelScope.launch {
            getLaunchDetails(params).collect {
                it.handle(::handleState, ::handleFailure, ::handleSuccess)
            }
        }
    }
    // endregion
}
