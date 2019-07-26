package com.melih.list.ui.paging

import com.melih.core.base.paging.BasePagingDataSource
import com.melih.core.base.paging.BasePagingFactory
import com.melih.repository.entities.LaunchEntity
import javax.inject.Inject
import javax.inject.Provider

class LaunchesPagingSourceFactory @Inject constructor(
    private val sourceProvider: Provider<LaunchesPagingSource>
) : BasePagingFactory<LaunchEntity>() {

    override fun createSource(): BasePagingDataSource<LaunchEntity> = sourceProvider.get()
}
