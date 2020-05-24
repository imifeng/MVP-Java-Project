package com.android.project.utils;

import android.text.TextUtils;
import android.util.Log;

import com.android.project.BuildConfig;
import com.android.project.net.gson.GsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Finn
 * @date 2020
 */
public class GsonUtils {

    public static volatile GsonUtils mInstance;

    public static Gson mGson;

    public static GsonUtils get() {
        if (mInstance == null) {
            synchronized (GsonSerializer.class) {
                if (mInstance == null)
                    mInstance = new GsonUtils();
            }
        }
        return mInstance;
    }

    public GsonUtils() {
        mGson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonSerializer())
                .create();
    }

    public Gson getGson() {
        return mGson;
    }

    public String toJson(Map object) {
        return mGson.toJson(object);
    }

    public String toJson(Object object) {
        if (object == null) {
            return "";
        } else if (object instanceof String && TextUtils.isEmpty((String) object)) {
            return "";
        }
        return mGson.toJson(object);
    }

    public <T> Object fromJsonT(String json, Class<T> cls) {
        return mGson.fromJson(json, cls);
    }

    public Object fromJson(String json, Class<?> cls) {
        return mGson.fromJson(json, cls);
    }

    public <T> T fromJsonSafe(String json, Type typeOfT) {
        try {
            return mGson.fromJson(json, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T fromJson(String json, Type type) {
        return mGson.fromJson(json, type);
    }


}
