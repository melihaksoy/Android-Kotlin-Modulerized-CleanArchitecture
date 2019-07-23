package com.melih.list.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.melih.core.base.paging.BasePagingFactory
import com.melih.core.base.viewmodel.BasePagingViewModel
import com.melih.core.extensions.containsIgnoreCase
import com.melih.list.ui.paging.LaunchesPagingSourceFactory
import com.melih.repository.entities.LaunchEntity
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    private val launchesPagingSourceFactory: LaunchesPagingSourceFactory,
    private val launchesPagingConfig: PagedList.Config
) : BasePagingViewModel<LaunchEntity>() {

    override val factory: BasePagingFactory<LaunchEntity>
        get() = launchesPagingSourceFactory

    override val config: PagedList.Config
        get() = launchesPagingConfig

    // region Properties

    private val _filteredItems = MediatorLiveData<List<LaunchEntity>>()

    val filteredItems: LiveData<List<LaunchEntity>>
        get() = _filteredItems
    // endregion

    init {
        _filteredItems.addSource(pagedList, _filteredItems::setValue)
    }

    // region Functions

    fun filterItemListBy(query: String?) {
        _filteredItems.value = if (!query.isNullOrBlank()) {
            pagedList.value
                ?.filter {
                    it.rocket.name.containsIgnoreCase(query) || it.missions.any { it.description.containsIgnoreCase(query) }
                }
        } else {
            pagedList.value
        }
    }
    // endregion
}
