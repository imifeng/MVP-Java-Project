package com.android.project.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author Finn
 * @date 2020
 * 适配刘海屏幕高度
 */
public class DisplayCutoutUtils {

    private static final String TAG = "DisplayCutoutUtils";


    // adapt cutout ViewGroup:HeaderView
    public static void displayAdaptCutoutHeaderView(Context context, View view) {
        int pH = DisplayCutoutUtils.getStatusBarHeight(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = pH;
        view.setLayoutParams(params);
    }

    // adapt cutout ViewGroup:FrameLayout
    public static void displayAdaptCutoutFrameLayout(Context context, View view) {
        int pH = DisplayCutoutUtils.getStatusBarHeight(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = pH;
        view.setLayoutParams(params);
    }

    // adapt cutout ViewGroup:LinearLayout
    public static void displayAdaptCutoutLinearLayout(Context context, View view) {
        int pH = DisplayCutoutUtils.getStatusBarHeight(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = pH;
        view.setLayoutParams(params);
    }

    // adapt cutout ViewGroup:LinearLayout
    public static void displayAdaptCutoutLinearLayout(View view, int pH) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = pH;
        view.setLayoutParams(params);
    }

    // adapt cutout ViewGroup:RelativeLayout
    public static void displayAdaptCutoutRelativeLayout(Context context, View view) {
        int pH = DisplayCutoutUtils.getStatusBarHeight(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = pH;
        view.setLayoutParams(params);
    }

    // adapt cutout ViewGroup:RelativeLayout
    public static void displayAdaptCutoutRelativeLayout(Context context, View view, int extraMargin) {
        if(view.getLayoutParams() instanceof RelativeLayout.LayoutParams){
            int pH = DisplayCutoutUtils.getStatusBarHeight(context);
            ((RelativeLayout.LayoutParams)view.getLayoutParams()).topMargin = pH + extraMargin;
        }
    }
    // adapt status bar view height
    public static void adaptStatusBarHeight(Context context, View view) {
        int pH = DisplayCutoutUtils.getStatusBarHeight(context);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = pH;
        view.setLayoutParams(params);
    }

    /**
     * get statusBar height
     *
     * @param context c
     * @return h
     */
    public static int getStatusBarHeight(Context context) {
        try {
            int result = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            LogUtils.showLog(TAG, "getStatusBarHeight==========>" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}