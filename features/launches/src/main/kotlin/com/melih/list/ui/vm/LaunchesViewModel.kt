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

    //private val _filteredItems = MediatorLiveData<PagedList<LaunchEntity>>()

    //val filteredItems: LiveData<PagedList<LaunchEntity>>
    //    get() = _filteredItems
    // endregion

    //init {
    //    _filteredItems.addSource(pagedList, _filteredItems::setValue)
    //}

    // region Functions

    //fun filterItemListBy(query: String?) {
    //
    //    _filteredItems.value = if (!query.isNullOrBlank()) {
    //        pagedList.value
    //            ?.snapshot() as PagedList<LaunchEntity>
    //    } else {
    //        pagedList.value
    //    }
    //}
    // endregion
}
