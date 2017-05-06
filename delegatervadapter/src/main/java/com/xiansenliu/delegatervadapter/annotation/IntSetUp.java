package com.xiansenliu.delegatervadapter.annotation;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.xiansenliu.delegatervadapter.BaseVH;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author       xinliu
 * Date         4/20/17
 * Time         12:06 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IntSetUp {
    @LayoutRes
    int[] layoutIds();

    @NonNull
    Class bean() default Object.class;

    @NonNull
    Class<? extends BaseVH> vh() default BaseVH.class;

    boolean applyParentLayout() default false;
}
