package com.melih.rocketscience.di

import com.melih.core.di.CoreComponent
import com.melih.detail.di.DetailModule
import com.melih.list.di.LaunchesModule
import com.melih.rocketscience.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [AndroidInjectionModule::class,
        LaunchesModule::class,
        DetailModule::class],

    dependencies = [CoreComponent::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): AppComponent
    }
}
