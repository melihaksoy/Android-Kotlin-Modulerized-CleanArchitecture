package com.melih.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.melih.core.base.recycler.BaseListAdapter
import com.melih.core.base.recycler.BaseViewHolder
import com.melih.list.databinding.LaunchRowBinding
import com.melih.repository.entities.LaunchEntity

class LaunchesAdapter(itemClickListener: (LaunchEntity) -> Unit) : BaseListAdapter<LaunchEntity>(
    object : DiffUtil.ItemCallback<LaunchEntity>() {
        override fun areItemsTheSame(oldItem: LaunchEntity, newItem: LaunchEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LaunchEntity, newItem: LaunchEntity): Boolean =
            oldItem.name == newItem.name

    },
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
        binding.tvDescription.text = if (missions.isNotEmpty()) missions[0].description else ""

        binding.executePendingBindings()
    }
}
