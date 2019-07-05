package com.melih.repository.interactors.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Base use case that wraps [suspending][suspend] [run] function with [flow][Flow] and returns it for later usage.
 */
abstract class BaseInteractor<T, in P : InteractorParameters> {

    // region Abstractions

    @ExperimentalCoroutinesApi
    protected abstract suspend fun run(collector: FlowCollector<Result<T>>, params: P)
    // endregion

    // region Functions

    @ExperimentalCoroutinesApi
    operator fun invoke(params: P) =
        flow<Result<T>> {
            emit(Result.State.Loading())
            run(this, params)
            emit(Result.State.Loaded())
        }.flowOn(Dispatchers.IO)
    // endregion
}

/**
 * Contract for parameter classes
 */
interface InteractorParameters

/**
 * Symbolizes absence of parameters for an [interactor][BaseInteractor]
 */
class None : Any(), InteractorParameters
