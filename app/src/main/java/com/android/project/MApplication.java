package com.android.project;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.android.project.utils.GlideImageUtils;

/**
 * @author Finn
 * @date 2020
 */
public class MApplication extends Application {

    private static MApplication instance;

    public static MApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //init
        instance = this;
    }


    public Boolean isContextExisted(Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed() && !((Activity) context).isFinishing()) {
                        return true;
                    }
                } else {
                    if (!((Activity) context).isFinishing()) {
                        return true;
                    }
                }
            } else if (context instanceof Application) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GlideImageUtils.getInstance().clearMemory(this);
    }

}
