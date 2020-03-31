package com.melih.rocketscience.di

import com.melih.detail.di.DetailContributor
import com.melih.launches.data.LaunchDetailItem
import com.melih.launches.di.LaunchesContributor
import com.melih.rocketscience.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {


    @ContributesAndroidInjector(
        modules = [
            LaunchesContributor::class,
            DetailContributor::class]
    )
    abstract fun mainActivity(): MainActivity

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provdeSomeObject() = LaunchDetailItem(10, "", "Rocket", "Desc")
    }
}
