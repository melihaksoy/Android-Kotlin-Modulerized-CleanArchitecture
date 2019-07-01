package com.melih.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Reduces required boilerplate code to observe a live data
 *
 * @param data [LiveData] to observe
 * @param block receive and process data
 */
fun <T> Fragment.observe(data: LiveData<T>, block: (T) -> Unit) {
    data.observe(this, Observer(block))
}

/**
 * Method for getting viewModel from factory and run a block over it if required for easy access
 *
 * crossinline for unwanted returns
 */
inline fun <reified T : ViewModel> ViewModelProvider.Factory.createFor(
    fragment: Fragment,
    crossinline block: T.() -> Unit = {}
): T {
    val viewModel = ViewModelProviders.of(fragment, this)[T::class.java]
    viewModel.apply(block)
    return viewModel
}
