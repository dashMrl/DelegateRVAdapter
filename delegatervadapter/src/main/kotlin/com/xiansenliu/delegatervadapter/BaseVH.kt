package com.xiansenliu.delegatervadapter

import android.support.v7.widget.RecyclerView
import android.view.View

open class BaseVH< in T>(root: View) : RecyclerView.ViewHolder(root) {
    open fun onBind(position: Int, items: MutableList<*>, model: T) {

    }
}