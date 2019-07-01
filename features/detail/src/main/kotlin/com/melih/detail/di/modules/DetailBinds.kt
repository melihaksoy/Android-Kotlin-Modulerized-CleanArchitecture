package com.melih.detail.di.modules

import androidx.lifecycle.ViewModel
import com.melih.core.di.keys.ViewModelKey
import com.melih.detail.ui.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class DetailBinds {

    // region ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @ExperimentalCoroutinesApi
    abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel
    // endregion
}
