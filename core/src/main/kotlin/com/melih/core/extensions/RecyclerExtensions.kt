package com.melih.core.extensions

import androidx.recyclerview.widget.DiffUtil

/**
 * Get [diff callback][DiffUtil.ItemCallback] for given type based on provided checker
 */
inline fun <T> getDiffCallbackForType(crossinline itemCheck: (oldItem: T, newItem: T) -> Boolean) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        itemCheck(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        itemCheck(oldItem, newItem)
}
