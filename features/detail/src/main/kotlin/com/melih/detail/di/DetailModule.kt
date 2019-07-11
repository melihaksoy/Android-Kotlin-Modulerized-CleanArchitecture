package com.melih.detail.di

import com.melih.detail.ui.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class DetailModule {

    // region Contributes

    @ContributesAndroidInjector(
        modules = [
            DetailContributor::class
        ]
    )
    abstract fun detailActivity(): DetailActivity
    // endregion
}
