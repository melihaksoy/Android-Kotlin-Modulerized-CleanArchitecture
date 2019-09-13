package com.melih.detail.di.modules

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.melih.core.di.keys.ViewModelKey
import com.melih.detail.ui.DetailFragment
import com.melih.detail.ui.DetailFragmentArgs
import com.melih.detail.ui.DetailViewModel
import com.melih.repository.interactors.GetLaunchDetails
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
