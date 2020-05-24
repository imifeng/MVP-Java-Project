package com.android.project.net.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * @author Finn
 * @date 2020
 */
public class GsonSerializer<T> implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType == String.class) {
            return (TypeAdapter<T>) new StringTypeAdapter();
        }
        if (rawType == Long.class) {
            return (TypeAdapter<T>) new LongTypeAdapter();
        }
        if (rawType == long.class) {
            return (TypeAdapter<T>) new LongTypeAdapter();
        }
        if (rawType == Integer.class) {
            return (TypeAdapter<T>) new IntTypeAdapter();
        }
        if (rawType == int.class) {
            return (TypeAdapter<T>) new IntTypeAdapter();
        }
        if (rawType == Double.class) {
            return (TypeAdapter<T>) new DoubleTypeAdapter();
        }
        if (rawType == double.class) {
            return (TypeAdapter<T>) new DoubleTypeAdapter();
        }
        if (rawType == Boolean.class) {
            return (TypeAdapter<T>) new BooleanTypeAdapter();
        }
        if (rawType == boolean.class) {
            return (TypeAdapter<T>) new BooleanTypeAdapter();
        }
        return null;
    }
}
