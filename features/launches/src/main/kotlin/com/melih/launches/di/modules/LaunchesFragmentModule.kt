package com.melih.launches.di.modules

import androidx.lifecycle.ViewModel
import androidx.paging.Config
import com.melih.abstractions.mapper.Mapper
import com.melih.core.di.keys.ViewModelKey
import com.melih.definitions.entities.LaunchEntity
import com.melih.interactors.DEFAULT_LAUNCHES_AMOUNT
import com.melih.interactors.GetLaunches
import com.melih.launches.data.LaunchItem
import com.melih.launches.data.LaunchMapper
import com.melih.launches.ui.vm.LaunchesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LaunchesFragmentModule {

    //region Binds

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    abstract fun launchesViewModel(listViewModel: LaunchesViewModel): ViewModel

    @Binds
    abstract fun launchMapper(mapper: LaunchMapper): Mapper<LaunchEntity, LaunchItem>
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
