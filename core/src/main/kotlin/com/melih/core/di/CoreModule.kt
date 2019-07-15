package com.melih.core.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.room.Room
import com.melih.repository.persistence.DB_NAME
import com.melih.repository.persistence.LaunchesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    fun provideNetworkInfo(app: Application): NetworkInfo? =
        (app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

    @Provides
    @Singleton
    internal fun provideLaunchesDatabase(app: Application) =
        Room.databaseBuilder(app.applicationContext, LaunchesDatabase::class.java, DB_NAME)
            .build()
}
