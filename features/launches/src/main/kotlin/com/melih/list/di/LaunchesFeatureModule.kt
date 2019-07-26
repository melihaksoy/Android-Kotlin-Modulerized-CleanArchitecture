package com.melih.list.di

import com.melih.list.di.scopes.LaunchesScope
import com.melih.list.ui.LaunchesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class LaunchesFeatureModule {

    // region Contributes

    @ContributesAndroidInjector(
        modules = [
            LaunchesContributor::class
        ]
    )
    @LaunchesScope
    abstract fun launchesActivity(): LaunchesActivity
    // endregion
}
