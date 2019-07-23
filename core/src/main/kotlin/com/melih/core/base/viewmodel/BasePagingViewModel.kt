package com.melih.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.melih.core.base.paging.BasePagingFactory
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.State

/**
 * Base [ViewModel] for view models that will use [PagedList].
 *
 * Since data handling is done via [be.mediahuis.core.base.paging.BasePagingDataSource], this view model doesn't need
 * a [kotlinx.coroutines.channels.ReceiveChannel] and will not provide any default operations of data, but instead will
 * provde [pagedList] which should be observed and submitted.
 *
 * If paging won't be used, use [BaseViewModel] instead.
 */
abstract class BasePagingViewModel<T> : ViewModel() {

    // region Abstractions

    abstract val factory: BasePagingFactory<T>
    abstract val config: PagedList.Config
    private lateinit var _pagedList: LiveData<PagedList<T>>
    // endregion

    // region Properties

    /**
     * Observe [stateData] to get notified of state of data
     */
    val stateData: LiveData<State> by lazy {
        Transformations.switchMap(factory.currentSource) {
            it.stateData
        }
    }

    /**
     * Observe [errorData] to get notified if an error occurs
     */
    val errorData: LiveData<Reason> by lazy {
        Transformations.switchMap(factory.currentSource) {
            it.reasonData
        }
    }

    /**
     * Observe [pagedList] to submit list it provides
     */
    val pagedList: LiveData<PagedList<T>> by lazy {
        LivePagedListBuilder(factory, config)
            .build()
    }
    // endregion

    fun refresh() {
        factory.currentSource.value?.invalidate()
    }
}
