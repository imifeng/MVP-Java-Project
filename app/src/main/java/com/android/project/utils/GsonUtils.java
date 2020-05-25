package com.android.project.utils;

import com.google.gson.Gson;

/**
 * @author Finn
 * @date 2020
 */
public class GsonUtils {

    private static final Gson sGson = new Gson();

    public static Gson get() {
        return sGson;
    }
}
