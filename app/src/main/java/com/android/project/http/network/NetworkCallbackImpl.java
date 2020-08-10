package com.android.project.http.network;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {
    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        // 在框架连接并声明可以使用新网络时调用
        if (networkConnectedListener != null)
            networkConnectedListener.networkConnected(true);
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        // 当网络断开连接或不再满足此请求或回调时调用
        if (networkConnectedListener != null)
            networkConnectedListener.networkConnected(false);
    }

    @Override
    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        // 当与此请求相对应的网络更改功能但仍满足请求的条件时调用
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilitiesListener == null) return;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                // 网络类型 wifi
                networkCapabilitiesListener.networkCapabilitiesChanged(NetworkType.NETWORK_WIFI);
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                // 蜂窝网络(CMWAP)
                networkCapabilitiesListener.networkCapabilitiesChanged(NetworkType.NETWORK_WAP);
            } else {
                // 其他网络
                networkCapabilitiesListener.networkCapabilitiesChanged(NetworkType.NETWORK_DEFAULT);
            }
        }
    }

    // 以下申明回调接口
    private NetworkConnectedListener networkConnectedListener = null;

    private NetworkCapabilitiesListener networkCapabilitiesListener = null;

    void addNetworkConnectedListener(NetworkConnectedListener listener) {
        this.networkConnectedListener = listener;
    }

    void addNetworkCapabilitiesListener(NetworkCapabilitiesListener listener) {
        this.networkCapabilitiesListener = listener;
    }

    void removeNetworkCallbackListener() {
        this.networkConnectedListener = null;
        this.networkCapabilitiesListener = null;
    }
}