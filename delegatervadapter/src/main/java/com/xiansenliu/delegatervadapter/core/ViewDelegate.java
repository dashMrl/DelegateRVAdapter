package com.xiansenliu.delegatervadapter.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       xinliu
 * Date         4/20/17
 * Time         12:13 AM
 */

public interface ViewDelegate {

    Class getKey();

    BaseVH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(RecyclerView.ViewHolder holder, Object bean, List items, int position);

    void onBindViewHolder(RecyclerView.ViewHolder holder, Object bean, List items, int position, Bundle bundle);

    @LayoutRes
    int getLayoutId();

}
