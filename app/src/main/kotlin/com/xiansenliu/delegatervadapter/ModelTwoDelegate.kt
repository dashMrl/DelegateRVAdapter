package com.xiansenliu.delegatervadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_model_two.view.*

/**
 * Author       xinliu
 * Date         9/14/17
 * Time         7:29 PM
 */
class ModelTwoDelegate : ModelDelegate<ModelTwo>() {
    override fun createVH(parent: ViewGroup?, position: Int, items: MutableList<*>): BaseVH<ModelTwo> {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_model_two, parent, false)
        return VH(view)
    }

    class VH(view: View) : BaseVH<ModelTwo>(view) {
        override fun onBind(position: Int, items: MutableList<*>, model: ModelTwo) {
            itemView.content_two.text = model.content

        }
    }
}