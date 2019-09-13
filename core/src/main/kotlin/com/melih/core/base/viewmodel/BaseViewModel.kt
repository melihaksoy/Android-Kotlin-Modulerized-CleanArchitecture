package com.melih.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.State
import kotlinx.coroutines.launch

/**
 * Base [ViewModel] for view models that will process data.
 *
 * This view model provides state & error with [stateData] & [errorData] respectively.
 */
abstract class BaseViewModel<T> : ViewModel() {

    //region Abstractions

    abstract suspend fun loadData()
    //endregion

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    //region Properties

    private val _successData = MutableLiveData<T>()
    private val _stateData = MutableLiveData<State>()
    private val _errorData = MutableLiveData<Reason>()

    /**
     * Observe [successData] to get notified of data if it's successfuly fetched
     */
    val successData: LiveData<T>
        get() = _successData

    /**
     * Observe [stateData] to get notified of state of data
     */
    val stateData: LiveData<State>
        get() = _stateData

    /**
     * Observe [errorData] to get notified if an error occurs
     */
    val errorData: LiveData<Reason>
        get() = _errorData
    //endregion

    //region Functions

    /**
     * Default success handler which assigns given [data] to [successData]
     *
     * @param data success data
     */
    protected fun handleSuccess(data: T) {
        _successData.value = data
    }

    /**
     * Default state handler which assigns given [state] to [stateData]
     *
     * @param state state of operation
     */
    protected fun handleState(state: State) {
        _stateData.value = state
    }

    /**
     * Default error handler which assign received [error] to [errorData]
     *
     * @param error check [Error] class for possible error types
     */
    protected fun handleFailure(reason: Reason) {
        _errorData.value = reason
    }

    /**
     * Reload data
     */
    fun refresh() {
        viewModelScope.launch {
            loadData()
        }
    }

    /**
     * Retry loading data, incase there's difference between refresh and retry, should go here
     */
    fun retry() {
        viewModelScope.launch {
            loadData()
        }
    }
    //endregion
}
