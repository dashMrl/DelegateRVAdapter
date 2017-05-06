package com.xiansenliu.delegatervadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by xinliu
 * Date       17-3-4
 * Time       13:17.
 */

public class DelegateAdapter<T> extends RecyclerView.Adapter {
    private static final String TAG = "DelegateAdapter";
    private List<T> mItems;

    private DelegateManager mDelegateManager;

    public DelegateAdapter(List<T> items) {
        this(items, new DefaultManager());
    }

    public DelegateAdapter(List<T> items, DelegateManager delegateManager) {
        this.mItems = items;
        mDelegateManager = new DefaultManager();
    }

    /**
     * Add delegate.
     *
     * @param delegates at least one delegate
     */
    public void addDelegates(@NonNull ViewDelegate... delegates) {
        if (delegates.length == 0) {
            try {
                throw new Exception("delegates' size should never be zero ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        for (ViewDelegate delegate : delegates) {
            mDelegateManager.addDelegate(delegate);
        }
    }

    public void removeDelegate(@NonNull ViewDelegate delegates) {
        mDelegateManager.removeDelegate(delegates);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = mDelegateManager.getItemViewType(mItems, position);

        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = mDelegateManager.onCreateViewHolder(parent, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegateManager.onBindViewHolder(holder, mItems, position, null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        mDelegateManager.onBindViewHolder(holder, mItems, position, payloads);
    }
}
