package com.melih.list.di.modules

import com.melih.list.ui.LaunchesAdapter
import com.melih.repository.interactors.GetLaunches
import dagger.Module
import dagger.Provides

@Module
class LaunchesProvides {

    /**
     * Provides lauches, using default value of 10
     */
    @Provides
    fun provideGetLaunchesParams() = GetLaunches.Params()
}
