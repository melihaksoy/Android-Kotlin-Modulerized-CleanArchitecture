package com.melih.detail.ui

import androidx.lifecycle.Transformations.map
import com.melih.abstractions.deliverable.handle
import com.melih.core.base.viewmodel.BaseViewModel
import com.melih.launches.data.LaunchDetailItem
import com.melih.repository.interactors.GetLaunchDetails
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getLaunchDetails: GetLaunchDetails<LaunchDetailItem>,
    private val getLaunchDetailsParams: GetLaunchDetails.Params
) : BaseViewModel<LaunchDetailItem>() {

    //region Properties

    val rocketName = map(successData) {
        it.rocketName
    }

    val description = map(successData) {
        it.missionDescription
    }

    val imageUrl = map(successData) {
        it.imageUrl
    }
    //endregion

    //region Functions

    /**
     * Triggering interactor in view model scope
     */
    override suspend fun loadData() {
        getLaunchDetails(getLaunchDetailsParams).collect {
            it.handle(::handleState, ::handleFailure, ::handleSuccess)
        }
    }
    //endregion
}
