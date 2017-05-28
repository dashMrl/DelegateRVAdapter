package com.xiansenliu.delegatervadapter.core;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xinliu
 * Date       17-3-4
 * Time       13:44.
 */

public class BaseVH extends RecyclerView.ViewHolder {
    private SparseArray<View> views;

    public BaseVH(View itemView) {
        super(itemView);
    }

    public <T extends View> T getView(int viewId, Class<T> viewType) {
        if (views == null) {
            views = new SparseArray<>();
        }
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return viewType.cast(view);
    }


    public BaseVH setTextViewText(@IdRes int viewId, CharSequence text) {
        getView(viewId, TextView.class).setText(text);
        return this;
    }
}
