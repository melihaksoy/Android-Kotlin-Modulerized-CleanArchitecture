package com.melih.detail.di.modules

import androidx.navigation.fragment.navArgs
import com.melih.detail.ui.DetailFragment
import com.melih.detail.ui.DetailFragmentArgs
import com.melih.repository.interactors.GetLaunchDetails
import dagger.Module
import dagger.Provides

@Module
class DetailProvides {

    /**
     * Provides launch detail params
     */
    @Provides
    fun provideGetLaunchDetailParams(fragment: DetailFragment): GetLaunchDetails.Params {
        val args: DetailFragmentArgs by fragment.navArgs()
        return GetLaunchDetails.Params(args.launchId)
    }
}
