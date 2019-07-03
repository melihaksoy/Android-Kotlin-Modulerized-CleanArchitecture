package com.melih.detail.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.navigation.fragment.navArgs
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.createFor
import com.melih.core.extensions.observe
import com.melih.detail.R
import com.melih.detail.databinding.DetailBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class DetailFragment : BaseDaggerFragment<DetailBinding>() {

    // region Properties

    private val args: DetailFragmentArgs by navArgs()

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

        viewModel.createParamsFor(args.launchId)
        viewModel.loadData()

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
