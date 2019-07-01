package com.melih.detail.di

import com.melih.detail.di.modules.DetailBinds
import com.melih.detail.ui.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class DetailContributor {

    // region Contributes

    @ContributesAndroidInjector(
        modules = [
            DetailBinds::class
        ]
    )
    abstract fun detailFragment(): DetailFragment
    // endregion
}
