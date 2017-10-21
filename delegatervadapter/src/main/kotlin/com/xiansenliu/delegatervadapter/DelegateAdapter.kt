package com.xiansenliu.delegatervadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         4:43 PM
 */
class DelegateAdapter(items: List<Any>,
                      private val manager: DelegateManager = DelegateManager())
    : RecyclerView.Adapter<BaseVH<Any>>() {
    private var items: MutableList<Any> = mutableListOf()

    init {
        this.items.addAll(items)
    }

    fun addHolderDelegate(vararg delegates: ModelDelegate<*>) {
        manager.addDelegates(*delegates)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        val type = manager.getItemViewType(position, items)
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseVH<Any> {
        val vh = manager.onCreateViewHolder(parent, viewType, items)
        return vh
    }

    override fun onBindViewHolder(holder: BaseVH<Any>?, position: Int) {
        manager.onBindViewHolder(holder, position, items)
    }


    fun updateItems(items: List<Any>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun insertItems(items: List<Any>, index: Int = this.items.size) {
        this.items.addAll(index, items)
        notifyItemRangeInserted(index, items.size)
    }

    fun removeItems(items: List<Any>) {
        this.items.removeAll(items)
        notifyDataSetChanged()
    }

}