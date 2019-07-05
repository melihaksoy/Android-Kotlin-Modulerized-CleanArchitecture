package com.melih.detail.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.createFor
import com.melih.core.extensions.observe
import com.melih.detail.R
import com.melih.detail.databinding.DetailBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DetailFragment : BaseDaggerFragment<DetailBinding>() {

    // region Properties

    @ExperimentalCoroutinesApi
    private val viewModel: DetailViewModel
        get() = viewModelFactory.createFor(this)
    // endregion

    // region Functions

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDescription.movementMethod = ScrollingMovementMethod()
        binding.viewModel = viewModel

        // Observing error to show toast with retry action
        observe(viewModel.errorData) {
            showSnackbarWithAction(it) {
                viewModel.retry()
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_detail
    // endregion
}
