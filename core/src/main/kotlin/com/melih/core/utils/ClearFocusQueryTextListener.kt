package com.melih.core.utils

import androidx.appcompat.widget.SearchView


/**
 * Simplifying [OnQueryTextListener][android.widget.SearchView.OnQueryTextListener]
 */
class ClearFocusQueryTextListener(
    private val view: SearchView,
    private val onQueryChangeBlock: (String?) -> Unit
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean {
        view.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        onQueryChangeBlock(newText)
        return true
    }
}
