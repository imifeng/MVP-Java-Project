package com.android.project.utils;

import android.widget.Toast;

import com.android.project.MApplication;

/**
 * @author Finn
 * @date 2020
 */
public class ToastUtils {

    public static void showToast(String msg) {
        Toast.makeText(MApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int msgResId) {
        Toast.makeText(MApplication.getInstance(), msgResId, Toast.LENGTH_SHORT).show();
    }
}
