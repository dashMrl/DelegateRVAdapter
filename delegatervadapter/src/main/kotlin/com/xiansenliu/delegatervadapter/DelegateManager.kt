package com.xiansenliu.delegatervadapter

import android.util.ArrayMap
import android.view.ViewGroup

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         4:56 PM
 */
class DelegateManager(private val items: MutableList<*>) {
    private val delegateMap = ArrayMap<Class<*>, BaseVHDelegate<*>>()


    fun addDelegates(vararg delegates: BaseVHDelegate<*>) {
        for (d in delegates) {
            val clazz = Util.getParameterizedClass(d)
            if (!delegateMap.contains(clazz)) {
                delegateMap.put(clazz, d)
            } else {
                delegateMap[clazz] = d
            }
        }
    }

    fun getItemViewType(position: Int): Int {
        return position
    }

    fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseVH {
        val vhDelegate = delegateMap[items[viewType]!!::class.java] ?: ErrorDelegate
        return vhDelegate.createVH(parent, viewType, items)
    }


    fun onBindViewHolder(holder: BaseVH?, position: Int) {
        holder?.onBind(position, items)
    }
}