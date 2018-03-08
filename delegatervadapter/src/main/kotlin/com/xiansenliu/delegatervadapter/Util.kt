package com.xiansenliu.delegatervadapter

import android.util.ArrayMap
import java.lang.reflect.ParameterizedType

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         8:37 PM
 */
class Util {
    companion object {
        fun getParameterizedClass(d: ModelDelegate<*>): Class<*> {
            val dataType = (d.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<*>
            return dataType
        }

        fun <K, V> findInMap(am: ArrayMap<K, V>, key: K): Int {
            return (0 until am.size).firstOrNull { am.keyAt(it) == key }
                    ?: UNSUPPORTED_VIEW_TYPE
        }
    }
}