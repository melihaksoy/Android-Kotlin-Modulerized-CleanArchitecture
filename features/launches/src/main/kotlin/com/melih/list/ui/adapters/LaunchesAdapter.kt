package com.melih.list.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.melih.core.base.recycler.BasePagingListAdapter
import com.melih.core.base.recycler.BaseViewHolder
import com.melih.core.extensions.getDiffCallbackForType
import com.melih.list.databinding.LaunchRowBinding
import com.melih.repository.entities.LaunchEntity

class LaunchesAdapter(itemClickListener: (LaunchEntity) -> Unit) : BasePagingListAdapter<LaunchEntity>(
    getDiffCallbackForType { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {
    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<LaunchEntity> =
        LaunchesViewHolder(LaunchRowBinding.inflate(inflater, parent, false))


}

class LaunchesViewHolder(private val binding: LaunchRowBinding) : BaseViewHolder<LaunchEntity>(binding) {

    override fun bind(item: LaunchEntity) {
        binding.entity = item

        val missions = item.missions
        binding.tvDescription.text = if (!missions.isNullOrEmpty()) missions[0].description else ""

        binding.executePendingBindings()
    }
}
