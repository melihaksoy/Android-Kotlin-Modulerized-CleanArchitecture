package com.melih.list.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.melih.core.actions.Actions
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.containsIgnoreCase
import com.melih.core.extensions.createFor
import com.melih.core.extensions.observe
import com.melih.core.extensions.setOnQueryChangedListener
import com.melih.list.R
import com.melih.list.databinding.ListBinding
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi

class LaunchesFragment : BaseDaggerFragment<ListBinding>(), SwipeRefreshLayout.OnRefreshListener {

    // region Properties

    @ExperimentalCoroutinesApi
    private val viewModel: LaunchesViewModel
        get() = viewModelFactory.createFor(this)

    private val launchesAdapter = LaunchesAdapter(::onItemSelected)
    private val itemList = mutableListOf<LaunchEntity>()
    // endregion

    // region Functions

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.rocketList.adapter = launchesAdapter
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        observeDataChanges()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_rocket_list, menu)
        setSearchQueryListener((menu.findItem(R.id.search).actionView as SearchView))
        super.onCreateOptionsMenu(menu, inflater)
    }

    @ExperimentalCoroutinesApi
    private fun observeDataChanges() {

        // Observing state to show loading
        observe(viewModel.stateData) {
            binding.swipeRefreshLayout.isRefreshing = it is Result.State.Loading
        }

        // Observing error to show toast with retry action
        observe(viewModel.errorData) {
            showSnackbarWithAction(it) {
                viewModel.retry()
            }
        }

        observe(viewModel.successData) {
            itemList.addAll(it)
            launchesAdapter.submitList(itemList)
            binding.rocketList.scheduleLayoutAnimation()
        }
    }

    private fun onItemSelected(item: LaunchEntity) {
        startActivity(Actions.openDetailFor(item.id))
    }

    private fun setSearchQueryListener(searchView: SearchView) {
        searchView.setOnQueryChangedListener {
            filterItemListBy(it)
        }
    }

    private fun filterItemListBy(query: String?) =
        if (!query.isNullOrBlank()) {
            itemList.filter {
                it.rocket.name.containsIgnoreCase(query) || it.missions.any { it.description.containsIgnoreCase(query) }
            }
        } else {
            itemList
        }

    @ExperimentalCoroutinesApi
    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun getLayoutId(): Int = R.layout.fragment_launches
    // endregion
}
