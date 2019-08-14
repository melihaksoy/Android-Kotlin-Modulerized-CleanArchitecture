package com.melih.detail.di.modules

import androidx.lifecycle.ViewModel
import com.melih.core.di.keys.ViewModelKey
import com.melih.detail.ui.DetailFragment
import com.melih.detail.ui.DetailViewModel
import com.melih.repository.interactors.GetLaunchDetails
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class DetailFragmentModule {

    // region ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel
    // endregion

    @Module
    companion object {

        /**
         * Provides launch detail params
         */
        @Provides
        @JvmStatic
        fun provideGetLaunchDetailParams(fragment: DetailFragment): GetLaunchDetails.Params {
            //val args: DetailFragmentArgs by fragment.navArgs()
            //return GetLaunchDetails.Params(args.launchId)
            return GetLaunchDetails.Params(10)
        }
    }
}
