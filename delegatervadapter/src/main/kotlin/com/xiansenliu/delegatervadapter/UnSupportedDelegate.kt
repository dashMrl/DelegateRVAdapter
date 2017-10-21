package com.xiansenliu.delegatervadapter

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Author       xinliu
 * Date         9/14/17
 * Time         7:13 PM
 */
object UnSupportedDelegate : ModelDelegate<Any>() {
    override fun createVH(parent: ViewGroup?, position: Int, items: MutableList<*>): BaseVH<Any> {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.delegate_adpter_error_item, parent, false)
        return BaseVH(view)
    }
}