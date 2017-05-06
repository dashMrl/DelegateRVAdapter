package com.xiansenliu.delegatervadapter;

import android.util.ArrayMap;

import java.lang.reflect.Field;

/**
 * Author       xinliu
 * Date         4/26/17
 * Time         1:44 PM
 *
 * store the resource IDs
 */

public final class RPool {
    private ArrayMap<String, Class<?>> classTable = new ArrayMap<>();
    private ArrayMap<Class<?>, ArrayMap<String, Integer>> poolTable = new ArrayMap<>();
    private ArrayMap<String, Integer> layoutPool = new ArrayMap<>();
    private ArrayMap<String, Integer> drawablePool = new ArrayMap<>();
    private static RPool sInstance;
    private static final String LAYOUT = "layout";
    private static final String DRAWABLE = "drawable";

    public static RPool getInstance() {
        if (sInstance == null) {
            sInstance = new RPool();
        }
        return sInstance;
    }

    private RPool() {
        classTable.put(LAYOUT, R.layout.class);
        poolTable.put(R.layout.class, layoutPool);
        classTable.put(DRAWABLE, R.drawable.class);
        poolTable.put(R.drawable.class, drawablePool);
    }

    public int getLayoutId(String layoutStr) {
        return getResId(LAYOUT, layoutStr);
    }

    public void putLayoutId(String layoutStr, int layoutId) {
        putResId(LAYOUT,layoutStr,layoutId);
    }

    private void putResId(String type, String resIdStr, int resId) {
        ArrayMap<String, Integer> resPool = getResPool(type);
        resPool.put(resIdStr, resId);
    }

    private int getResId(String type, String resIdStr) {
        ArrayMap<String, Integer> resPool = getResPool(type);
        int id;
        if (resPool.containsKey(resIdStr)) {
            id = resPool.get(resIdStr);
        } else {
            Class<?> resClass = classTable.get(type);
            try {
                Field field = resClass.getField(resIdStr);
                id = field.getInt(null);
                resPool.put(resIdStr, id);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return id;
    }

    private ArrayMap<String, Integer> getResPool(String type) {
        Class<?> resClass = classTable.get(type);
        ArrayMap<String, Integer> resPool = poolTable.get(resClass);
        return resPool;
    }
}
