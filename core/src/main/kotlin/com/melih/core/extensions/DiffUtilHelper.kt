package com.melih.core.extensions

import androidx.recyclerview.widget.DiffUtil

/**
 * Get [diff callback][DiffUtil.ItemCallback] for given type based on provided checker.
 * It uses [itemCheck] for both [DiffUtil.ItemCallback.areItemsTheSame] and [DiffUtil.ItemCallback.areContentsTheSame].
 */
inline fun <T> createDiffCallback(crossinline itemCheck: (oldItem: T, newItem: T) -> Boolean) = createDiffCallback(itemCheck, itemCheck)

/**
 * Get [diff callback][DiffUtil.ItemCallback] for given type based on provided checker
 */
inline fun <T> createDiffCallback(
    crossinline itemCheck: (oldItem: T, newItem: T) -> Boolean,
    crossinline contentCheck: (oldItem: T, newItem: T) -> Boolean
) = object : DiffUtil.ItemCallback<T>() {

    //region Functions

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        itemCheck(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        contentCheck(oldItem, newItem)
    //endregion
}
