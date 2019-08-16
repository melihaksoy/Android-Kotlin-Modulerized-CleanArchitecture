package com.melih.rocketscience.di

import com.melih.detail.di.DetailContributor
import com.melih.list.di.LaunchesContributor
import com.melih.rocketscience.MainActivity
import com.melih.rocketscience.navigation.NavigationHandler
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {


    @ContributesAndroidInjector(
        modules = [
            LaunchesContributor::class,
            DetailContributor::class]
    )
    abstract fun mainActivity(): MainActivity
}
