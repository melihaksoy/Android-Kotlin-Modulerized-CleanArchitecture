package com.melih.list.di.modules

import androidx.lifecycle.ViewModel
import com.melih.core.di.keys.ViewModelKey
import com.melih.list.ui.vm.LaunchesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LaunchesBinds {

    // region ViewModels

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    abstract fun listViewModel(listViewModel: LaunchesViewModel): ViewModel
    // endregion
}
