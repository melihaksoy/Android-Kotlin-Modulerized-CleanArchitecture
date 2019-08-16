package com.melih.rocketscience.navigation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.melih.core.actions.EXTRA_LAUNCH_ID
import com.melih.list.ui.LaunchesFragmentDirections

class NavigationReceiver constructor(
    private val navigationHandler: NavigationHandler
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action =
            LaunchesFragmentDirections
                .actionListToDetail()
                .setLaunchId(intent?.getLongExtra(EXTRA_LAUNCH_ID, 0L) ?: 0L)

        navigationHandler.navigate(action)
    }
}
