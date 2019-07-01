package com.melih.list.di.modules

import androidx.lifecycle.ViewModel
import com.melih.core.di.keys.ViewModelKey
import com.melih.list.ui.LaunchesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class LaunchesBinds {

    // region ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    @ExperimentalCoroutinesApi
    abstract fun listViewModel(listViewModel: LaunchesViewModel): ViewModel
    // endregion
}
