package com.melih.core.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.Module
import dagger.Provides

@Module
class CoreModule {

    @Provides
    fun proivdeAppContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideNetworkInfo(app: Application): NetworkInfo? =
        (app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
}
