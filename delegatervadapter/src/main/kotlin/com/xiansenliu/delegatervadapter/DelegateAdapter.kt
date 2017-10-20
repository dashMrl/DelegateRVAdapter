package com.xiansenliu.delegatervadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         4:43 PM
 */
class DelegateAdapter(private val items: MutableList<*>,
                      private val manager: DelegateManager = DelegateManager(items))
    : RecyclerView.Adapter<BaseVH>() {

    fun addHolderDelegate(vararg delegates: ModelDelegate<*>) {
        manager.addDelegates(*delegates)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return manager.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseVH {
        return manager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseVH?, position: Int) {
        manager.onBindViewHolder(holder, position)
    }

}