package com.melih.detail.di.modules

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.melih.abstractions.mapper.Mapper
import com.melih.core.di.keys.ViewModelKey
import com.melih.definitions.entities.LaunchEntity
import com.melih.detail.ui.DetailFragment
import com.melih.detail.ui.DetailFragmentArgs
import com.melih.detail.ui.DetailViewModel
import com.melih.interactors.GetLaunchDetails
import com.melih.launches.data.LaunchDetailItem
import com.melih.launches.data.LaunchDetailMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class DetailFragmentModule {

    //region ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun detailMapper(mapper: LaunchDetailMapper): Mapper<LaunchEntity, LaunchDetailItem>
    //endregion

    @Module
    companion object {

        /**
         * Provides launch detail params
         */
        @Provides
        @JvmStatic
        fun provideGetLaunchDetailParams(fragment: DetailFragment): GetLaunchDetails.Params {
            val args by fragment.navArgs<DetailFragmentArgs>()
            return GetLaunchDetails.Params(args.launchId)
        }
    }
}
