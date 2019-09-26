package com.melih.launches.di

import com.melih.launches.di.modules.LaunchesFragmentModule
import com.melih.launches.di.scopes.LaunchesFragmentScope
import com.melih.launches.ui.LaunchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class LaunchesContributor {

    //region Contributes

    @ContributesAndroidInjector(
        modules = [LaunchesFragmentModule::class]
    )
    @LaunchesFragmentScope
    abstract fun launchesFragment(): LaunchesFragment
    //endregion
}
