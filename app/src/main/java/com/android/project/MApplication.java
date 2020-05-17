package com.android.project;

import android.app.Application;

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
}
