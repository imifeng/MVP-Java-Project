package com.android.project.utils;

import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Finn
 * @date 2020
 * 点击事件响应效果设置
 */
public class OnClickUtils {

    public static void setOnClickListener(View view, View.OnClickListener onClickListener) {
        AtomicReference<Float> startX = new AtomicReference<>(0f);
        AtomicReference<Float> startY = new AtomicReference<>(0f);
        view.setOnTouchListener((v, motionEvent) -> {
            try {
                float x = motionEvent.getRawX();
                float y = motionEvent.getRawY();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setAlpha(0.8f);
                        startX.set(x);
                        startY.set(y);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setAlpha(1.0f);
                        if (Math.abs(x - startX.get()) < v.getWidth() / 2 && Math.abs(y - startY.get()) < v.getHeight() / 2) {
                            onClickListener.onClick(v);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        v.setAlpha(1.0f);
                        break;
                }
            } catch (Exception e) {
                LogUtils.showError(e);
            }
            return true;
        });
    }
}
