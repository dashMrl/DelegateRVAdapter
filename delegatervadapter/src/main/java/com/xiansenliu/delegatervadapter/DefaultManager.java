package com.xiansenliu.delegatervadapter;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.ViewGroup;

import com.xiansenliu.delegatervadapter.core.DelegateManager;
import com.xiansenliu.delegatervadapter.core.ViewDelegate;

import java.util.Arrays;
import java.util.List;

/**
 * Author       xinliu
 * Date         4/20/17
 * Time         10:31 AM
 */

public final class DefaultManager implements DelegateManager {
    private static final String TAG = "DefaultManager";
    private ArrayMap<Class, ViewDelegate> mDelegates = new ArrayMap<>();
    private ViewDelegate cachedViewDelegate = null;
    private int cachedIndex = 0;

    @Override
    public void addDelegate(ViewDelegate... delegates) {
        for (ViewDelegate delegate : delegates) {
            mDelegates.put(delegate.getKey(), delegate);
        }
//        cachedViewDelegate = delegates[0];
    }

    @Override
    public void removeDelegate(ViewDelegate... delegates) {
        mDelegates.removeAll(Arrays.asList(delegates));
    }

    @Override
    public int getItemViewType(List items, int position) {
        Object o = items.get(position);
        Class<?> key = o.getClass();
//        Log.i(TAG, "getItemViewType: ");
        if (cachedViewDelegate == null || cachedViewDelegate.getKey() != key) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cachedIndex = mDelegates.indexOfKey(key);
                cachedViewDelegate = mDelegates.valueAt(cachedIndex);
//                Log.i(TAG, "getItemViewType: LOLLIPOP and above " + position + "--" + cachedViewDelegate);
                return cachedIndex;
            } else {
                int size = mDelegates.size();
                for (int i = 0; i < size; i++) {
                    ViewDelegate viewDelegate = mDelegates.valueAt(i);
                    if (viewDelegate.getKey() == key) {
                        cachedViewDelegate = viewDelegate;
                        cachedIndex = i;
//                        Log.i(TAG, "getItemViewType: LOLLIPOP below" + position + "--" + cachedViewDelegate);
                        return cachedIndex;
                    }
                }
            }
        } else {
//            Log.i(TAG, "getItemViewType: cached " + position + "--" + cachedViewDelegate);
            return cachedIndex;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDelegate viewDelegate = mDelegates.valueAt(viewType);
        RecyclerView.ViewHolder vh = cachedViewDelegate.onCreateViewHolder(parent);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int position, List payloads) {
//        Log.i(TAG, "onBindViewHolder: " + position + "--" + cachedViewDelegate);
        Object o = items.get(position);
//        Class<?> key = o.getClass();

//        ViewDelegate viewDelegate = mDelegates.get(key);
//        if (key != cachedViewDelegate.getKey()) {
//            getItemViewType(items, position);
//        }
        if (payloads == null || payloads.size() == 0) {
            cachedViewDelegate.onBindViewHolder(holder, o, items, position);
        } else /*if (payloads.size() != 0)*/ {

            cachedViewDelegate.onBindViewHolder(holder, o, items, position, ((Bundle) payloads.get(0)));
        }
    }
}
