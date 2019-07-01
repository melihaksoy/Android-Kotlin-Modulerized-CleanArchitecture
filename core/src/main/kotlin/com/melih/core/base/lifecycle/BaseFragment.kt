package com.melih.core.base.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * Parent of all fragments.
 *
 * Purpose of [BaseFragment] is to simplify view creation and provide easy access to fragment's
 * [navController] and [binding].
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    // region Properties

    protected lateinit var navController: NavController
    protected lateinit var binding: T
    // endregion

    // region Functions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = NavHostFragment.findNavController(this)
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    // endregion
}
