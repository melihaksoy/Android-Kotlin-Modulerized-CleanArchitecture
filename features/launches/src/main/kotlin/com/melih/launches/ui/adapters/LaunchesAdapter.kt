package com.melih.launches.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.melih.core.base.recycler.BasePagingListAdapter
import com.melih.core.base.recycler.BaseViewHolder
import com.melih.core.extensions.createDiffCallback
import com.melih.launches.databinding.LaunchRowBinding
import com.melih.repository.entities.LaunchEntity

class LaunchesAdapter(itemClickListener: (LaunchEntity) -> Unit) : BasePagingListAdapter<LaunchEntity>(
    createDiffCallback { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {

    //region Functions

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<LaunchEntity> =
        LaunchesViewHolder(LaunchRowBinding.inflate(inflater, parent, false))
    //endregion
}

class LaunchesViewHolder(private val binding: LaunchRowBinding) : BaseViewHolder<LaunchEntity>(binding) {

    //region Functions

    override fun bind(item: LaunchEntity) {
        binding.entity = item

        val missions = item.missions
        binding.tvDescription.text = if (!missions.isNullOrEmpty()) missions[0].description else ""

        binding.executePendingBindings()
    }
    //endregion
}
