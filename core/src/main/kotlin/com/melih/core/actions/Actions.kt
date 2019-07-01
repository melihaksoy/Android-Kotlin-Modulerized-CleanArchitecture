package com.melih.core.actions

import android.content.Intent

const val EXTRA_LAUNCH_ID = "extras:detail:launchid"

/**
 * Navigation actions for navigation between feature activities
 */
object Actions {

    fun openDetailFor(id: Long) =
        Intent("action.dashboard.open")
            .putExtra(EXTRA_LAUNCH_ID, id)

}
