package com.melih.core.extensions

import androidx.appcompat.widget.SearchView
import com.melih.core.utils.ClearFocusQueryTextListener

/**
 * Shorthand for [contains] with ignoreCase set [true]
 */
fun CharSequence.containsIgnoreCase(other: CharSequence) = contains(other, true)

fun SearchView.setOnQueryChangedListener(block: (String?) -> Unit) = setOnQueryTextListener(ClearFocusQueryTextListener(this, block))
