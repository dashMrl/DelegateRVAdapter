package com.xiansenliu.delegatervadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiansenliu.delegatervadapter.annotation.StrSetUp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author       xinliu
 * Date         4/26/17
 * Time         12:05 PM
 * <p>
 * ViewDelegate used in android lib module
 */
public abstract class LibViewDelegate implements ViewDelegate {
    private static final String TAG = "LibViewDelegate";
    private List<Integer> mLayoutIds = new ArrayList<>();
    protected Class mBean = null;
    protected Class<? extends BaseVH> mVHTypeClass = null;
    private boolean isApplyParentLayout;
    private Constructor mVHConstructor = null;
    protected Context mContext;
    protected LayoutInflater mInflater;
    private String[] temLayoutStrs = new String[]{};

    @Override
    public Class getKey() {
        return mBean;
    }

    public LibViewDelegate() {
        StrSetUp setUp = this.getClass().getAnnotation(StrSetUp.class);
        temLayoutStrs = setUp.layoutStrs();
        mBean = setUp.bean();
        mVHTypeClass = setUp.vh();
        isApplyParentLayout = setUp.applyParentLayout();
        if (mVHTypeClass != BaseVH.class) {
            mVHConstructor = ConstructorPool.getInstance().get(mVHTypeClass);
        }
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
            mLayoutIds.clear();
            for (String layoutStr : temLayoutStrs) {
                int layoutId = getLayoutId(layoutStr, mContext);
                mLayoutIds.add(layoutId);
            }
            temLayoutStrs = null;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Object bean, List items, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Object bean, List items, int position, Bundle bundle) {

    }

    @Override
    public int getLayoutId() {
        return mLayoutIds.get(0);
    }

    private static int getLayoutId(String layoutStr, Context context) {
        RPool rPool = RPool.getInstance();
        int layoutId = rPool.getLayoutId(layoutStr);
        if (layoutId == -1) {
            Log.i(TAG, "getLayoutId: find failed , use resource");
            layoutId = context.getResources().getIdentifier(layoutStr, "layout", context.getPackageName());
            rPool.putLayoutId(layoutStr, layoutId);
        }
        return layoutId;
    }
}
