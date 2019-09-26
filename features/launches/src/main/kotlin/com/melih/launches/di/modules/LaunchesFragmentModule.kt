package com.melih.launches.di.modules

import androidx.lifecycle.ViewModel
import androidx.paging.Config
import com.melih.core.di.keys.ViewModelKey
import com.melih.launches.ui.vm.LaunchesViewModel
import com.melih.repository.interactors.DEFAULT_LAUNCHES_AMOUNT
import com.melih.repository.interactors.GetLaunches
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LaunchesFragmentModule {

    //region ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    abstract fun listViewModel(listViewModel: LaunchesViewModel): ViewModel
    //endregion

    @Module
    companion object {

        /**
         * Provides lauches, using default value of 15
         */
        @Provides
        @JvmStatic
        fun provideGetLaunchesParams() = GetLaunches.Params(page = 0)

        @Provides
        @JvmStatic
        fun getPagingConfig() = Config(
            DEFAULT_LAUNCHES_AMOUNT,
            prefetchDistance = 2,
            enablePlaceholders = false
        )
    }
}
