package com.melih.list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.melih.core.actions.openDetail
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.observe
import com.melih.list.R
import com.melih.list.databinding.ListBinding
import com.melih.list.ui.adapters.LaunchesAdapter
import com.melih.list.ui.vm.LaunchesViewModel
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.PersistenceEmpty
import com.melih.repository.interactors.base.State

class LaunchesFragment : BaseDaggerFragment<ListBinding>(), SwipeRefreshLayout.OnRefreshListener {

    //region Properties

    private val viewModel by viewModels<LaunchesViewModel> { viewModelFactory }

    private val launchesAdapter = LaunchesAdapter(::onItemSelected)
    //endregion

    //region Lifecyle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setHasOptionsMenu(true)

        binding.rocketList.adapter = launchesAdapter
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        observeDataChanges()
    }

    override fun onResume() {
        super.onResume()

        // Workaround for SwipeRefreshLayout leak -> https://issuetracker.google.com/issues/136153683
        binding.swipeRefreshLayout.isEnabled = true
    }

    override fun onPause() {
        super.onPause()

        // Workaround for SwipeRefreshLayout leak -> https://issuetracker.google.com/issues/136153683
        binding.swipeRefreshLayout.isEnabled = false
    }
    //endregion

    //region Functions

    private fun observeDataChanges() {

        // Observing state to show loading
        observe(viewModel.stateData) {
            binding.swipeRefreshLayout.isRefreshing = it is State.Loading
        }

        // Observing error to show toast with retry action
        observe(viewModel.errorData) {
            if (it !is PersistenceEmpty) {
                showSnackbarWithAction(it) {
                    viewModel.retry()
                }
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
    //endregion
}
