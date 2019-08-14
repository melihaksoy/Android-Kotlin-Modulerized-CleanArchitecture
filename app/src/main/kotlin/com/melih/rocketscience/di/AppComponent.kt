package com.melih.rocketscience.di

import com.melih.core.di.CoreComponent
import com.melih.detail.di.DetailContributor
import com.melih.list.di.LaunchesContributor
import com.melih.rocketscience.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [AndroidInjectionModule::class,
        LaunchesContributor::class,
        DetailContributor::class],

    dependencies = [CoreComponent::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): AppComponent
    }
}
