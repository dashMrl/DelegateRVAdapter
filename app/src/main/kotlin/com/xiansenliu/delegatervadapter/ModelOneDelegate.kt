package com.xiansenliu.delegatervadapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_model_one.view.*
import kotlinx.android.synthetic.main.item_model_one_another.view.*

/**
 * Author       xinliu
 * Date         9/14/17
 * Time         6:53 PM
 */
class ModelOneDelegate : ModelDelegate<ModelOne>() {
    override fun createVH(parent: ViewGroup?, position: Int, items: MutableList<*>): BaseVH {
        val inflater = LayoutInflater.from(parent!!.context)
        return if (position % 4 == 0) {
            Log.i("Delegate", "VH")
            val view = inflater.inflate(R.layout.item_model_one, parent, false)
            VH(view)
        } else {
            Log.i("Delegate", "VHAnother")
            val view = inflater.inflate(R.layout.item_model_one_another, parent, false)
            VHAnother(view)
        }
    }

    class VH(view: View) : BaseVH(view) {
        override fun onBind(position: Int, items: MutableList<*>) {
            itemView.content_one.text = (items[position] as ModelOne).content
        }
    }

    class VHAnother(view: View) : BaseVH(view) {
        override fun onBind(position: Int, items: MutableList<*>) {
            itemView.content_one_another.text = (items[position] as ModelOne).content
            itemView.content_one_another.setTextColor(Color.RED)
        }
    }
}