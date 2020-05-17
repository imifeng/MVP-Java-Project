package com.android.project.utils;

import android.util.Log;

import com.android.project.BuildConfig;

/**
 * @author Finn
 * @date 2020
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    public static void showLog(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void showLog(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void showError(String tag, Exception e) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, e.getMessage());
        }
    }

    public static void showError(Exception e) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, e.getMessage());
        }
    }

}
