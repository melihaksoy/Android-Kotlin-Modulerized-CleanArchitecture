package com.melih.launches.ui.vm

import androidx.paging.PagedList
import com.melih.core.base.paging.BasePagingFactory
import com.melih.core.base.viewmodel.BasePagingViewModel
import com.melih.launches.data.LaunchItem
import com.melih.launches.ui.paging.LaunchesPagingSourceFactory
import com.melih.repository.entities.LaunchEntity
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    private val launchesPagingSourceFactory: LaunchesPagingSourceFactory,
    private val launchesPagingConfig: PagedList.Config
) : BasePagingViewModel<LaunchItem>() {

    //region Properties

    override val factory: BasePagingFactory<LaunchItem>
        get() = launchesPagingSourceFactory

    override val config: PagedList.Config
        get() = launchesPagingConfig
    //endregion
}
