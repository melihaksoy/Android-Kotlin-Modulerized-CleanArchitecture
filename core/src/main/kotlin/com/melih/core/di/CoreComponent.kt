package com.melih.core.di

import android.app.Application
import android.net.NetworkInfo
import com.melih.repository.persistence.LaunchesDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun getNetworkInfo(): NetworkInfo?

    fun getLaunchesDatabase(): LaunchesDatabase

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }
}
