package com.android.project.base;

import com.android.project.net.RetrofitClient;

/**
 * @author Finn
 * @date 2020
 */
public class BaseModel {

    protected RetrofitClient mRepository;//用于管理网络请求层, 以及数据缓存层

    public BaseModel() {
        mRepository = RetrofitClient.getInstance();
    }

    public RetrofitClient getRepository() {
        return mRepository;
    }
}
