package com.xiansenliu.delegatervadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiansenliu.delegatervadapter.annotation.IntSetUp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by xinliu
 * Date       17-3-4
 * Time       13:19.
 * <p>
 * ViewDelegate used in Android app module
 */
public abstract class BaseViewDelegate implements ViewDelegate {
    private static final String TAG = "BaseViewDelegate";
    private int[] mLayoutIds;
    protected Class mBean = null;
    protected Class<? extends BaseVH> mVHTypeClass = null;
    private boolean isApplyParentLayout;
    private Constructor mVHConstructor = null;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public BaseViewDelegate() {
        IntSetUp setUp = this.getClass().getAnnotation(IntSetUp.class);
        mLayoutIds = setUp.layoutIds();
        mBean = setUp.bean();
        mVHTypeClass = setUp.vh();
        Log.i(TAG, "BaseViewDelegate: " + mVHTypeClass.toString());
        isApplyParentLayout = setUp.applyParentLayout();

        if (mVHTypeClass != BaseVH.class) {
            mVHConstructor = ConstructorPool.getInstance().get(mVHTypeClass);
        }
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        View itemView = mInflater.inflate(getLayoutId(), isApplyParentLayout ? parent : null, false);
        if (mVHConstructor == null) {
            return new BaseVH(itemView);
        }
        try {
            return (BaseVH) mVHConstructor.newInstance(itemView);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Object bean, List items, int position, Bundle bundle) {
    }

    public boolean setApplyPrentLayout(boolean apply) {
        this.isApplyParentLayout = apply;
        return this.isApplyParentLayout;
    }

    @Override
    public int getLayoutId() {
        return mLayoutIds[0];
    }

    @Override
    public Class getKey() {
        return mBean;
    }
}