package com.melih.rocketscience.di

import com.melih.detail.di.DetailContributor
import com.melih.detail.ui.DetailActivity
import com.melih.list.di.LaunchesContributor
import com.melih.list.ui.LaunchesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(
        modules = [
            LaunchesContributor::class
        ]
    )
    abstract fun launchesActivity(): LaunchesActivity

    @ContributesAndroidInjector(
        modules = [
            DetailContributor::class
        ]
    )
    abstract fun detailActivity(): DetailActivity
}
