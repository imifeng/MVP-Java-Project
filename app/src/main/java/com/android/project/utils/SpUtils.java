package com.android.project.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.android.project.BuildConfig;
import com.android.project.MApplication;

/**
 * @author Finn
 * @date 2020
 * 封装Sp工具类
 */
public class SpUtils {

    public static final String SP_PREFIX = BuildConfig.APPLICATION_ID + ".SHARED_PREF";
    public static final String SHARED_PREF = SP_PREFIX +"_" + BuildConfig.BUILD_TYPE;

    private static SpUtils spUtils;
    private static SharedPreferences sp;

    public static SpUtils getInstance() {
        if (spUtils == null) {
            synchronized (SpUtils.class) {
                if (spUtils == null)
                    spUtils = new SpUtils();
            }
        }
        return spUtils;
    }

    public SpUtils() {
        sp = MApplication.getInstance().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    }

    /**
     * get saved value
     */
    public String getString(String key, String defaultParam) {
        return sp.getString(key, defaultParam);
    }

    public int getInt(String key, int defaultParam) {
        return sp.getInt(key, defaultParam);
    }

    public Boolean getBoolean(String key, Boolean defaultParam) {
        return sp.getBoolean(key, defaultParam);
    }

    public Float getFloat(String key, Float defaultParam) {
        return sp.getFloat(key, defaultParam);
    }

    public Long getFloat(String key, Long defaultParam) {
        return sp.getLong(key, defaultParam);
    }

    /**
     * save value
     */
    public void saveValue(String key, Object defaultParam) {
        if (defaultParam instanceof String) {
            sp.edit().putString(key, (String) defaultParam).apply();
        } else if (defaultParam instanceof Integer) {
            sp.edit().putInt(key, (Integer) defaultParam).apply();
        } else if (defaultParam instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) defaultParam).apply();
        } else if (defaultParam instanceof Float) {
            sp.edit().putFloat(key, (Float) defaultParam).apply();
        } else if (defaultParam instanceof Long) {
            sp.edit().putLong(key, (Long) defaultParam).apply();
        } else {
            //...
        }
    }

    /**
     * remove value
     */
    public void removeValue(String key) {
        sp.edit().remove(key).apply();
    }

}
