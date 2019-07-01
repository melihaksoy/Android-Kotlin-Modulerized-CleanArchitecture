package com.melih.core.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * [Factory][ViewModelProvider.Factory] that provides view models allowing injection. [viewModelMap] is provided via dagger
 * injection. To be able to inject a view model, it must be bound to map via [dagger.Binds] [dagger.multibindings.IntoMap]
 * by using [ViewModelKey][com.melih.core.di.keys.ViewModelKey].
 *
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel> = viewModelMap[modelClass]
            ?: throw IllegalArgumentException("Unknown ViewModel")

        return try {
            viewModelProvider.get() as T
                ?: throw IllegalArgumentException("Provider's contained value is null")
        } catch (e: ClassCastException) {
            throw e
        }
    }
}
