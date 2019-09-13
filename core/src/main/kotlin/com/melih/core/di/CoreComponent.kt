package com.melih.core.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun getAppContext(): Context

    fun getConnectivityManagerInfo(): ConnectivityManager?

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }
}
