package com.melih.detail.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.viewModels
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.observe
import com.melih.detail.R
import com.melih.detail.databinding.DetailBinding

class DetailFragment : BaseDaggerFragment<DetailBinding>() {

    //region Properties

    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }
    //endregion

    //region Functions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDescription.movementMethod = ScrollingMovementMethod()
        binding.viewModel = viewModel

        // Observing error to show toast with retry action
        observe(viewModel.errorData) {
            showSnackbarWithAction(it) {
                viewModel.loadData()
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_detail
    //endregion
}
