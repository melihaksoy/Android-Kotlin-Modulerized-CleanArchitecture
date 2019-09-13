package com.melih.core.base.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Base adapter to reduce boilerplate on creating / binding view holders.
 */
abstract class BasePagingListAdapter<T>(
    callback: DiffUtil.ItemCallback<T>,
    private val clickListener: (T) -> Unit
) : PagedListAdapter<T, BaseViewHolder<T>>(callback) {

    //region Abstractions

    /**
     * This method will be called to create view holder to obfuscate layout inflation creation / process
     *
     * @param inflater layout inflator
     * @param parent parent view group
     * @param viewType viewType of holder
     */
    abstract fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T>
    //endregion

    //region Functions

    /**
     * [createViewHolder] will provide holders, no need to override this
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        createViewHolder(
            LayoutInflater.from(parent.context),
            parent,
            viewType
        )

    /**
     * Calls [bind][BaseViewHolder.bind] on view holders
     */
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        getItem(position)?.also { item ->

            holder.itemView.setOnClickListener { view ->
                clickListener(item)
            }

            holder.bind(item)
        }
    }
    //endregion
}

/**
 * Base view holder takes view data binding
 */
abstract class BaseViewHolder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    //region Functions

    /**
     * Items are delivered to [bind] via [BaseListAdapter.onBindViewHolder]
     *
     * @param item entity
     * @param position position from adapter
     */
    abstract fun bind(item: T)
    //endregion
}
