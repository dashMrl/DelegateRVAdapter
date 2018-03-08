package com.xiansenliu.delegatervadapter

import android.util.ArrayMap
import android.view.ViewGroup

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         4:56 PM
 */
class DelegateManager {
    private val delegateMap = ArrayMap<Class<*>, ModelDelegate<*>>()
    private val helper = TypeHelper()

    fun addDelegates(vararg delegates: ModelDelegate<*>) {
        for (d in delegates) {
            val clazz = Util.getParameterizedClass(d)
            if (!delegateMap.contains(clazz)) {
                delegateMap.put(clazz, d)
            } else {
                delegateMap[clazz] = d
            }
        }
    }

    fun getItemViewType(position: Int, items: MutableList<Any>): Int {
        return helper.matchType(items[position], delegateMap)
    }

    fun onCreateViewHolder(parent: ViewGroup?, viewType: Int, items: MutableList<Any>): BaseVH<Any> {
        if (viewType == UNSUPPORTED_VIEW_TYPE) {
            return UnSupportedDelegate.createVH(parent, viewType, items)
        }
        val vhDelegate = delegateMap.valueAt(viewType) ?: UnSupportedDelegate
        return vhDelegate.createVH(parent, viewType, items) as BaseVH<Any>
    }


    fun onBindViewHolder(holder: BaseVH<Any>?, position: Int, items: MutableList<*>) {
        holder?.onBind(position, items, checkNotNull(items[position]) { "null Object should never be added in" })
    }

    private class TypeHelper {
        private val indexCache = ArrayMap<Class<*>, Int>()
        fun matchType(keyObj: Any, map: ArrayMap<Class<*>, ModelDelegate<*>>): Int {
            val key = keyObj::class.java
            val i = indexCache[key]
            if (i != null) {
                return i
            } else {
                val type = Util.findInMap(map, key)
                indexCache.put(key, type)
                return type
            }
        }

    }
}