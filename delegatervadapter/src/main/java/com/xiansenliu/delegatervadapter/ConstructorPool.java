package com.xiansenliu.delegatervadapter;

import android.util.ArrayMap;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;

/**
 * Author       xinliu
 * Date         4/20/17
 * Time         11:33 AM
 */

public final class ConstructorPool {
    private static ConstructorPool sInstance;

    public static ConstructorPool getInstance() {
        if (sInstance == null) {
            sInstance = new ConstructorPool();
        }
        return sInstance;
    }

    private ConstructorPool() {
    }

    private ArrayMap<Class<? extends BaseVH>, Constructor> pool;

    public Constructor put(Class<? extends BaseVH> cls, Constructor constructor) {
        if (pool == null) {
            pool = new ArrayMap<>();
        }
        return pool.put(cls, constructor);
    }

    public Constructor get(Class<? extends BaseVH> cls) {
        if (pool == null) {
            pool = new ArrayMap<>();
        }

        Constructor constructor = null;
        if (pool.size() == 0 || (constructor = pool.get(cls)) == null) {
            try {
                constructor = cls.getConstructor(View.class);
                pool.put(cls, constructor);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "get: " + constructor.toString());
        return constructor;

    }

    private static final String TAG = "ConstructorPool";

    public Constructor remove(Class<? extends BaseVH> cls) {
        if (pool == null || pool.size() == 0) {
            return null;
        }
        return pool.remove(cls);
    }

    public void clear() {
        if (pool != null && pool.size() > 0) {
            pool.clear();
        }
    }
}
