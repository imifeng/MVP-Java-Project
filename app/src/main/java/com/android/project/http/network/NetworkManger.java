package com.android.project.http.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.project.ui.MApplication;

/**
 * @author Finn
 * @date 2020
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
public class NetworkManger {
    private static volatile NetworkManger networkManger;

    public static NetworkManger getInstance() {
        if (networkManger == null) {
            synchronized (NetworkManger.class) {
                if (networkManger == null) {
                    networkManger = new NetworkManger();
                }
            }
        }
        return networkManger;
    }

    private static ConnectivityManager connectivityManager = (ConnectivityManager) MApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

    private static NetworkCallbackImpl networkCallbackImpl = new NetworkCallbackImpl();

    public void registerNetworkCallback(NetworkConnectedListener listener) {
        this.connectedListener = listener;
        connectivityManager.registerNetworkCallback(
                new NetworkRequest.Builder().build(),
                networkCallbackImpl
        );

        /**
         * 网络监听回调(默认)
         */
        networkCallbackImpl.addNetworkConnectedListener(isConnected -> {
            if (connectedListener != null)
                connectedListener.networkConnected(isConnected);
        });

        /**
         * 添加网络类型监听回调
         */
        networkCallbackImpl.addNetworkCapabilitiesListener(type -> {
            if (capabilitiesListener != null)
                capabilitiesListener.networkCapabilitiesChanged(type);
        });
    }

    public void unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallbackImpl);
        this.connectedListener = null;
        this.capabilitiesListener = null;
    }

    private NetworkConnectedListener connectedListener = null;

    private NetworkCapabilitiesListener capabilitiesListener = null;

    /**
     * 添加网络类型监听回调
     */
    public void addNetworkCapabilitiesListener(NetworkCapabilitiesListener listener) {
        this.capabilitiesListener = listener;
    }

}
