package com.melih.list.di.modules

import com.melih.repository.interactors.GetLaunches
import dagger.Module
import dagger.Provides

@Module
class LaunchesProvides {

    /**
     * Provides lauches, using default value of 15
     */
    @Provides
    fun provideGetLaunchesParams() = GetLaunches.Params(page = 0)
}
