package com.melih.list.di

import com.melih.list.ui.LaunchesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class LaunchesModule {

    // region Contributes

    @ContributesAndroidInjector(
        modules = [
            LaunchesContributor::class
        ]
    )
    abstract fun launchesActivity(): LaunchesActivity
    // endregion
}
