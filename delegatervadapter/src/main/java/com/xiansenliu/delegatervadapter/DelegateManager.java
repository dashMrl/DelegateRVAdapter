package com.xiansenliu.delegatervadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author       xinliu
 * Date         4/20/17
 * Time         10:25 AM
 */

public interface DelegateManager {
    void addDelegate(ViewDelegate... delegate);

    void removeDelegate(ViewDelegate... delegate);

    int getItemViewType(List items, int position);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder holder, List items, int position, List payloads);
}
