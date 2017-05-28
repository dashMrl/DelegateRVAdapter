package com.xiansenliu.delegatervadapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiansenliu.delegatervadapter.core.BaseVH;
import com.xiansenliu.delegatervadapter.core.ViewDelegate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Author       xinliu
 * Date         5/21/17
 * Time         12:01 AM
 */

public abstract class BaseDelegate implements ViewDelegate {
    private static final String TAG = "BaseViewDelegate";
    private SetUp mSetUp;
    private Constructor mVHConstructor = null;

    public BaseDelegate() {
        mSetUp = getSetup();
        if (mSetUp.vh != BaseVH.class) {
            mVHConstructor = ConstructorPool.getInstance().get(mSetUp.vh);
        }
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), mSetUp.applyParent ? parent : null, false);
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
        this.mSetUp.applyParent = apply;
        return this.mSetUp.applyParent;
    }

    @Override
    public int getLayoutId() {
        return mSetUp.layouts[0];
    }

    @Override
    public Class getKey() {
        return mSetUp.bean;
    }

    @NonNull
    protected abstract SetUp getSetup();

    /**
     * Author       xinliu
     * Date         5/20/17
     * Time         11:52 PM
     */
    public static class SetUp {
        public Class<? extends BaseVH> vh;
        public Class bean;
        public boolean applyParent;
        public int[] layouts;

        public SetUp(@Nullable Class<? extends BaseVH> vh, @Nullable Class bean, boolean applyParent, int... layouts) {
            this.vh = vh == null ? BaseVH.class : vh;
            this.bean = bean == null ? Object.class : bean;
            this.applyParent = applyParent;
            this.layouts = layouts;
        }
    }
}
