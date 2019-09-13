package com.melih.core.base.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * Base [factory][DataSource.Factory] class for any [dataSource][DataSource]s in project.
 *
 * It's purpose is to provide latest source so [basePagingViewModel][be.mediahuis.core.base.viewmodel.BasePagingViewModel] can obtain
 * [stateData][BasePagingDataSource.stateData] and [reasonData][BasePagingDataSource.reasonData] from it.
 *
 * This is done under the hood by telling this factory how to create a source by overriding [createSource].
 *
 * Purpose of this transmission is to encapuslate [basePagingDataSource][BasePagingDataSource].
 */
abstract class BasePagingFactory<T> : DataSource.Factory<Int, T>() {

    //region Abstractions

    abstract fun createSource(): BasePagingDataSource<T>
    //endregion

    //region Properties

    private val _currentSource = MutableLiveData<BasePagingDataSource<T>>()

    val currentSource: LiveData<BasePagingDataSource<T>>
        get() = _currentSource
    //endregion

    //region Functions

    override fun create(): DataSource<Int, T> = createSource().apply { _currentSource.postValue(this) }

    /**
     * Invalidating the [currentSource]
     * by calling [BasePagingDataSource.invalidate]
     */
    fun invalidateDataSource() = currentSource.value?.invalidate()
    //endregion
}
