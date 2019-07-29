package com.melih.detail.di

import com.melih.detail.ui.DetailActivity
import com.melih.list.di.scopes.DetailScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contributes fragments & view models in this module
 */
@Module
abstract class DetailFeatureModule {

    // region Contributes

    @ContributesAndroidInjector(
        modules = [
            DetailContributor::class
        ]
    )
    @DetailScope
    abstract fun detailActivity(): DetailActivity
    // endregion
}
