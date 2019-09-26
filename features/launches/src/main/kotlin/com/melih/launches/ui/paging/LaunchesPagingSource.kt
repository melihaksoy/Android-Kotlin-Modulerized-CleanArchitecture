package com.melih.launches.ui.paging

import com.melih.core.base.paging.BasePagingDataSource
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.GetLaunches
import com.melih.repository.interactors.base.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Uses [GetLaunches] to get data for pagination
 */
class LaunchesPagingSource @Inject constructor(
    private val getLaunches: GetLaunches,
    private val getLaunchesParams: GetLaunches.Params
) : BasePagingDataSource<LaunchEntity>() {

    //region Functions

    @UseExperimental(ExperimentalCoroutinesApi::class)
    override fun loadDataForPage(page: Int): Flow<Result<List<LaunchEntity>>> =
        getLaunches(
            getLaunchesParams.copy(
                page = page
            )
        )
    //endregion
}
