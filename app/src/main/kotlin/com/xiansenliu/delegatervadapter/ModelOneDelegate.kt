package com.xiansenliu.delegatervadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_model_one.view.*

/**
 * Author       xinliu
 * Date         9/14/17
 * Time         6:53 PM
 */
class ModelOneDelegate : ModelDelegate<ModelOne>() {
    override fun createVH(parent: ViewGroup?, position: Int, items: MutableList<*>): BaseVH<ModelOne> {

        val inflater = LayoutInflater.from(parent!!.context)
        Log.i("Delegate", "VH")
        val view = inflater.inflate(R.layout.item_model_one, parent, false)
        return VH(view)
    }


    class VH(view: View) : BaseVH<ModelOne>(view) {
        override fun onBind(position: Int, items: MutableList<*>, model: ModelOne) {
            itemView.content_one.text = (items[position] as ModelOne).content
            super.onBind(position, items, model)
        }
    }

}