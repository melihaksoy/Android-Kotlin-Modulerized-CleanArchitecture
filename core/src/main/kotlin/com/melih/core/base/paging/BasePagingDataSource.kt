package com.melih.core.base.paging

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.Result
import com.melih.repository.interactors.base.State
import com.melih.repository.interactors.base.onFailure
import com.melih.repository.interactors.base.onState
import com.melih.repository.interactors.base.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

const val INITIAL_PAGE = 0

/**
 * Base class for all [pageKeyedDataSources][PageKeyedDataSource] in project.
 *
 * Purpose of this class is to ease handling of [result][Result]. It overrides [loadInitial], [loadAfter] and [loadBefore]
 * so sources extends from base does not need to override them, they just need to provide way of loading data by overriding [loadDataForPage].
 *
 * [handleState] & [handleFailure] updates corresponding [liveData][LiveData] objects [stateData] & [reasonData],
 * which can be used with [androidx.lifecycle.Transformations] to observe changes on the source state & error.
 *
 * This source has it's own [coroutineScope][CoroutineScope] that's backed up by a [SupervisorJob] to handle networking operations.
 * It's cancelled automatically when source factory [invalidates][invalidate] the source.
 */
abstract class BasePagingDataSource<T> : PageKeyedDataSource<Int, T>() {

    // region Abstractions

    @ExperimentalCoroutinesApi
    abstract fun loadDataForPage(page: Int): Flow<Result<List<T>>> // Load next page(s)
    // endregion

    // region Properties

    private val _stateData = MutableLiveData<State>()
    private val _reasonData = MutableLiveData<Reason>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    /**
     * Observe [stateData] to get notified of state of data
     */
    val stateData: LiveData<State>
        get() = _stateData

    /**
     * Observe [reasonData] to get notified if an error occurs
     */
    val reasonData: LiveData<Reason>
        get() = _reasonData
    // endregion

    // region Functions

    @ExperimentalCoroutinesApi
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        // Looping through channel as we'll receive any state, error or data here
        loadDataForPage(INITIAL_PAGE)
            .onEach { result ->
                result.onState(::handleState)
                    .onFailure(::handleFailure)
                    .onSuccess {
                        // When we receive data without any failures, we transform it and return list, also what's the value for next page
                        callback.onResult(
                            it,
                            INITIAL_PAGE,
                            INITIAL_PAGE + 1
                        )
                    }
            }
            .launchIn(coroutineScope)
    }

    @ExperimentalCoroutinesApi
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // Key for which page to load is in params
        val page = params.key

        loadDataForPage(page)
            .onEach { result ->
                result
                    .onState(::handleState)
                    .onFailure(::handleFailure)
                    .onSuccess {
                        // When we receive data without any failures, we transform it and return list, also what's the value for next page
                        callback.onResult(
                            it,
                            page + 1
                        )
                    }
            }
            .launchIn(coroutineScope)
    }

    /**
     * This loads previous pages, we don't have a use for it yet, so it's a no-op override
     */
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // no-op
    }

    /**
     * Default state handler which assigns given [state] to [stateData]
     *
     * @param state state of operation
     */
    @CallSuper
    protected fun handleState(state: State) {
        _stateData.value = state
    }

    /**
     * Default error handler which assign received [reason] to [reasonData]
     *
     * @param reason check [Reason] class for possible error types
     */
    @CallSuper
    protected fun handleFailure(reason: Reason) {
        _reasonData.value = reason
    }

    /**
     * Canceling [coroutineScope]
     */
    @CallSuper
    override fun invalidate() {
        coroutineScope.cancel()
        super.invalidate()
    }
    // endregion
}
