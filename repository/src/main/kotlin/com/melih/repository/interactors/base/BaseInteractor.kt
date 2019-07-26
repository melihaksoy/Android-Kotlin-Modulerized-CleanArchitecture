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
@UseExperimental(ExperimentalCoroutinesApi::class)
abstract class BaseInteractor<T, in P : InteractorParameters> {

    // region Abstractions

    protected abstract suspend fun FlowCollector<Result<T>>.run(params: P)
    // endregion

    // region Functions

    operator fun invoke(params: P) =
        flow<Result<T>> {
            emit(State.Loading())
            run(params)
            emit(State.Loaded())
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
