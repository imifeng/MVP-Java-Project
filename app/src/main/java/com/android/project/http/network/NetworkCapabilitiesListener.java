package com.android.project.http.network;

/**
 * 添加网络类型监听回调
 */
public interface NetworkCapabilitiesListener {
    void networkCapabilitiesChanged(NetworkType type);
}
