package com.melih.rocketscience.di

import com.melih.detail.di.DetailContributor
import com.melih.launches.di.LaunchesContributor
import com.melih.rocketscience.MainActivity
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
