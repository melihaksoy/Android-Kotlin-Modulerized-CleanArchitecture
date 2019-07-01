package com.melih.core.base.lifecycle

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val NAV_HOST_FRAGMENT_TAG = "nav_host_fragment_tag"

/**
 * Base class of all Activity classes
 */
abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity() {

    protected lateinit var binding: T
    protected lateinit var navHostFragment: NavHostFragment

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this

        if (savedInstanceState == null) {
            navHostFragment = createNavHostFragment()

            supportFragmentManager
                .beginTransaction()
                .add(addNavHostTo(), navHostFragment, NAV_HOST_FRAGMENT_TAG)
                .commitNow()
        } else {
            navHostFragment = supportFragmentManager
                .findFragmentByTag(NAV_HOST_FRAGMENT_TAG) as NavHostFragment
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!NavigationUI.navigateUp(navHostFragment.navController, null)) {
            onBackPressed()
        }

        return true
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun createNavHostFragment(): NavHostFragment

    @IdRes
    abstract fun addNavHostTo(): Int
}
