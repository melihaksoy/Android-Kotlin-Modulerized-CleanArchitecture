package com.melih.detail.ui

import androidx.lifecycle.Transformations
import com.melih.core.base.viewmodel.BaseViewModel
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.GetLaunchDetails
import com.melih.repository.interactors.base.handle
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getLaunchDetails: GetLaunchDetails,
    private val getLaunchDetailsParams: GetLaunchDetails.Params
) : BaseViewModel<LaunchEntity>() {

    // region Properties

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

    /**
     * Triggering interactor in view model scope
     */
    override suspend fun loadData() {
        getLaunchDetails(getLaunchDetailsParams).collect {
            it.handle(::handleState, ::handleFailure, ::handleSuccess)
        }
    }
    // endregion
}
