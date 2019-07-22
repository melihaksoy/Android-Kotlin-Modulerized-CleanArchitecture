package com.melih.core.extensions

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.melih.core.utils.ClearFocusQueryTextListener

/**
 * Shorthand for [contains] with ignoreCase set [true]
 */
fun CharSequence.containsIgnoreCase(other: CharSequence) = contains(other, true)

fun SearchView.setOnQueryChangedListener(block: (String?) -> Unit) = setOnQueryTextListener(ClearFocusQueryTextListener(this, block))

fun MenuItem.onExpandOrCollapse(onExpand: () -> Unit, onCollapse: () -> Unit) {
    setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            onCollapse()
            return true
        }

        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            onExpand()
            return true
        }
    })
}
