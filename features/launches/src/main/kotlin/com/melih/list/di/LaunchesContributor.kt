package com.melih.list.di

import com.melih.list.di.modules.LaunchesBinds
import com.melih.list.di.modules.LaunchesProvides
import com.melih.list.di.scopes.LaunchesFragmentScope
import com.melih.list.ui.LaunchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class LaunchesContributor {

    // region Contributes

    @ContributesAndroidInjector(
        modules = [
            LaunchesProvides::class,
            LaunchesBinds::class
        ]
    )
    @LaunchesFragmentScope
    abstract fun launchesFragment(): LaunchesFragment
    // endregion
}
