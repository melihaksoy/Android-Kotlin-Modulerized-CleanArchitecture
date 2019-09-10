package com.melih.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Reduces required boilerplate code to observe a live data
 *
 * @param data [LiveData] to observe
 * @param block receive and process data
 */
fun <T> Fragment.observe(data: LiveData<T>, block: (T) -> Unit) {
    data.observe(this, Observer(block))
}
