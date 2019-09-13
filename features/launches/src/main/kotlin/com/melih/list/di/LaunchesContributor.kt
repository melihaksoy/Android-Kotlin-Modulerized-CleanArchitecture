package com.melih.list.di

import com.melih.list.di.modules.LaunchesFragmentModule
import com.melih.list.di.scopes.LaunchesFragmentScope
import com.melih.list.ui.LaunchesFragment
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
