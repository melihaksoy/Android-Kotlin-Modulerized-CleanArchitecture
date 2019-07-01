package com.melih.core.base.lifecycle

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.melih.core.di.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * Parent of fragments which has injections. Aim is to seperate [BaseFragment] functionality for fragments which
 * won't need any injection.
 *
 * Note that fragments that extends from [BaseDaggerFragment] should contribute android injector.
 *
 * This class provides [viewModelFactory] which serves as factory for view models
 * in the project. It's injected by map of view models that this app is serving. Check [ViewModelFactory]
 * to see how it works.
 */
abstract class BaseDaggerFragment<T : ViewDataBinding> : BaseFragment<T>(), HasSupportFragmentInjector {

    // region Properties

    @get:Inject
    internal var childFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    // endregion

    // region Functions

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = childFragmentInjector
    // endregion
}
