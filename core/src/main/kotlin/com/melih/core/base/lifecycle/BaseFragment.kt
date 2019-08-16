package com.melih.core.base.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.melih.core.R
import com.melih.repository.interactors.base.Reason

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
    protected lateinit var broadcastManager: LocalBroadcastManager
    // endregion

    // region Functions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        broadcastManager = LocalBroadcastManager.getInstance(requireContext())
        navController = NavHostFragment.findNavController(this)
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    protected fun showSnackbarWithAction(reason: Reason, block: () -> Unit) {
        Snackbar.make(
            binding.root,
            resources.getString(reason.messageRes),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.retry) {
            block()
        }.show()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    // endregion
}
