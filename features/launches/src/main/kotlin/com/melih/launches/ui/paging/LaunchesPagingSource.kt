package com.melih.launches.ui.paging

import com.melih.core.base.paging.BasePagingDataSource
import com.melih.interactors.GetLaunches
import com.melih.launches.data.LaunchItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Uses [GetLaunches] to get data for pagination
 */
class LaunchesPagingSource @Inject constructor(
    private val getLaunches: GetLaunches<LaunchItem>,
    private val getLaunchesParams: GetLaunches.Params
) : BasePagingDataSource<LaunchItem>() {

    //region Functions

    @UseExperimental(ExperimentalCoroutinesApi::class)
    override fun loadDataForPage(page: Int) =
        getLaunches(
            getLaunchesParams.copy(
                page = page
            )
        )
    //endregion
}
