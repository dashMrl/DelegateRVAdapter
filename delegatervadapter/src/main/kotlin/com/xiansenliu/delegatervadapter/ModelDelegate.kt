package com.xiansenliu.delegatervadapter

import android.view.ViewGroup

/**
 * Author       xinliu
 * Date         8/28/17
 * Time         7:31 PM
 */
abstract class ModelDelegate<in T> {
    fun canDelegate(t: T): Boolean = false

    abstract fun createVH(parent: ViewGroup?, position:Int,items: MutableList<*>): BaseVH

}