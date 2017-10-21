package com.xiansenliu.delegatervadapter

import android.util.ArrayMap

/**
 * Author       xinliu
 * Date         10/21/17
 * Time         12:08 PM
 */
fun <K, V> ArrayMap<K, V>.indexOfKeyCompatible(key: K): Int {

    for (i in 0 until size) {
        if (keyAt(i) == key) {

            return i
        }
    }
    return UNSUPPORTED_VIEW_TYPE
}