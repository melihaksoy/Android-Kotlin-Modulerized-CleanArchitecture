package com.melih.launches.ui.paging

import com.melih.core.base.paging.BasePagingDataSource
import com.melih.core.base.paging.BasePagingFactory
import com.melih.launches.data.LaunchItem
import javax.inject.Inject
import javax.inject.Provider

class LaunchesPagingSourceFactory @Inject constructor(
    private val sourceProvider: Provider<LaunchesPagingSource>
) : BasePagingFactory<LaunchItem>() {

    //region Functions

    override fun createSource(): BasePagingDataSource<LaunchItem> = sourceProvider.get()
    //endregion
}
