package com.melih.list.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.melih.core.actions.Actions
import com.melih.core.base.lifecycle.BaseDaggerFragment
import com.melih.core.extensions.createFor
import com.melih.core.extensions.observe
import com.melih.list.R
import com.melih.list.databinding.ListBinding
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

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

        // Observing state to show loading
        observe(viewModel.stateData) {
            binding.swipeRefreshLayout.isRefreshing = it is Result.State.Loading
        }

        // Observing error to show toast with retry action
        observe(viewModel.errorData) {
            Snackbar.make(
                binding.root,
                resources.getString(it.messageRes),
                Snackbar.LENGTH_INDEFINITE
            ).setAction(com.melih.core.R.string.retry) {
                viewModel.retry()
            }.show()
        }

        observe(viewModel.successData) {
            itemList.addAll(it)
            launchesAdapter.submitList(itemList)
            binding.rocketList.scheduleLayoutAnimation()
        }
    }

    private fun onItemSelected(item: LaunchEntity) {
        Timber.i("${item.id}")
        startActivity(Actions.openDetailFor(item.id))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_rocket_list, menu)

        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    launchesAdapter.submitList(
                        if (!newText.isNullOrBlank()) {
                            itemList.filter {
                                it.rocket.name.contains(
                                    newText,
                                    true
                                ) || (it.missions.size > 0 && it.missions[0].description.contains(
                                    newText,
                                    true
                                ))
                            }
                        } else {
                            itemList
                        }
                    )

                    return true
                }
            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    @ExperimentalCoroutinesApi
    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun getLayoutId(): Int = R.layout.fragment_launches
    // endregion
}
