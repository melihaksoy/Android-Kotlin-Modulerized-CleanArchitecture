package com.melih.list.ui.vm

import androidx.paging.PagedList
import com.melih.core.base.paging.BasePagingFactory
import com.melih.core.base.viewmodel.BasePagingViewModel
import com.melih.list.ui.paging.LaunchesPagingSourceFactory
import com.melih.repository.entities.LaunchEntity
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    private val launchesPagingSourceFactory: LaunchesPagingSourceFactory,
    private val launchesPagingConfig: PagedList.Config
) : BasePagingViewModel<LaunchEntity>() {

    // region Properties

    override val factory: BasePagingFactory<LaunchEntity>
        get() = launchesPagingSourceFactory

    override val config: PagedList.Config
        get() = launchesPagingConfig
    // endregion
}
