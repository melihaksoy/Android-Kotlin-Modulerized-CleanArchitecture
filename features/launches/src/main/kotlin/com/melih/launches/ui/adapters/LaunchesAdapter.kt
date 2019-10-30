package com.melih.launches.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.melih.core.base.recycler.BasePagingListAdapter
import com.melih.core.base.recycler.BaseViewHolder
import com.melih.core.extensions.createDiffCallback
import com.melih.launches.data.LaunchItem
import com.melih.launches.databinding.LaunchRowBinding

class LaunchesAdapter(itemClickListener: (LaunchItem) -> Unit) : BasePagingListAdapter<LaunchItem>(
    createDiffCallback { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {

    //region Functions

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<LaunchItem> =
        LaunchesViewHolder(LaunchRowBinding.inflate(inflater, parent, false))
    //endregion
}

class LaunchesViewHolder(private val binding: LaunchRowBinding) :
    BaseViewHolder<LaunchItem>(binding) {

    //region Functions

    override fun bind(item: LaunchItem) {
        binding.entity = item
        binding.executePendingBindings()
    }
    //endregion
}
