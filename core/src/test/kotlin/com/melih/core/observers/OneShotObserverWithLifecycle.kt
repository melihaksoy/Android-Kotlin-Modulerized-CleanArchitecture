package com.melih.core.observers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

/**
 * This class is both [Observer] & [LifecycleOwner], used to observe on live data via
 * [testObserve].
 *
 * Taking continuation is due to suspending coroutine, else scope is getting closed right away after
 * reaching end of suspending job and test is over.
 */
class OneShotObserverWithLifecycle<T>(
    val block: (T) -> Unit, val
    continuation: Continuation<Unit>
) : LifecycleOwner, Observer<T> {

    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        block(t)
        continuation.resume(Unit)
    }
}
