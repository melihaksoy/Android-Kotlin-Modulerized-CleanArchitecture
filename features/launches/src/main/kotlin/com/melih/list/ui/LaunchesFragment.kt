package com.melih.list.ui

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.melih.core.actions.openDetail
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.createFor
import com.melih.core.extensions.observe
import com.melih.list.R
import com.melih.list.databinding.ListBinding
import com.melih.list.ui.adapters.LaunchesAdapter
import com.melih.list.ui.vm.LaunchesViewModel
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.State

class LaunchesFragment : BaseDaggerFragment<ListBinding>(), SwipeRefreshLayout.OnRefreshListener {

    // region Properties

    private val viewModel: LaunchesViewModel
        get() = viewModelFactory.createFor(this)

    private val launchesAdapter = LaunchesAdapter(::onItemSelected)
    // endregion

    // region Functions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setHasOptionsMenu(true)

        binding.rocketList.adapter = launchesAdapter
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        observeDataChanges()
    }

    private fun observeDataChanges() {

        // Observing state to show loading
        observe(viewModel.stateData) {
            binding.swipeRefreshLayout.isRefreshing = it is State.Loading
        }

        // Observing error to show toast with retry action
        observe(viewModel.errorData) {
            showSnackbarWithAction(it) {
                viewModel.retry()
            }
        }

        observe(viewModel.pagedList) {
            launchesAdapter.submitList(it)
        }
    }

    private fun onItemSelected(item: LaunchEntity) {
        openDetail(item.id)
    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun getLayoutId(): Int = R.layout.fragment_launches
    // endregion
}
