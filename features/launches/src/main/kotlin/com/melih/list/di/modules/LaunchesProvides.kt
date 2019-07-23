package com.melih.list.di.modules

import androidx.paging.Config
import com.melih.repository.interactors.DEFAULT_LAUNCHES_AMOUNT
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

    @Provides
    fun getPagingConfig() = Config(DEFAULT_LAUNCHES_AMOUNT)
}
