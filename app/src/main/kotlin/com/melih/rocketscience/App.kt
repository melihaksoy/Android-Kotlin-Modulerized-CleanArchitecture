package com.melih.rocketscience

import com.melih.core.di.DaggerCoreComponent
import com.melih.launches.data.LaunchDetailItem
import com.melih.rocketscience.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory()
            .create(
                DaggerCoreComponent.factory()
                    .create(this)
            )

    @Inject
    lateinit var item: LaunchDetailItem

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
