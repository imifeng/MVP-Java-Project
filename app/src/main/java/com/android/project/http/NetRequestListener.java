package com.android.project.http;

/**
 * @author Finn
 * @date 2020
 */
public interface NetRequestListener<T> {
    void onSuccess(T response);

    void onFailed(Throwable e);
}
