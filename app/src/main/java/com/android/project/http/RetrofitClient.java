package com.android.project.http;

import android.text.TextUtils;

import com.android.project.BuildConfig;
import com.android.project.manager.SharedPreferencesManager;
import com.android.project.mvp.model.bean.User;
import com.android.project.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Finn
 * @date 2020
 * 封装Retrofit
 */
public class RetrofitClient {
    private static volatile RetrofitClient mRetrofitClient;
    private Retrofit retrofit;

    public static RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null)
                    mRetrofitClient = new RetrofitClient();
            }
        }
        return mRetrofitClient;
    }

    public RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(getHeaderInterceptor()) //设置Header
                .addInterceptor(getInterceptor()) //设置拦截器
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        okHttpClient.dispatcher().setMaxRequestsPerHost(20);

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.getBaseHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public  <T> T create(Class<T> service){
        return retrofit.create(service);
    }


    /**
     * 设置拦截器
     *
     * @return
     */
    private Interceptor getInterceptor() {
        return new LoggingInterceptor(message -> LogUtils.showLog("RetrofitLog", message));
    }

    /**
     * 设置Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
            //这里把token添加到请求头，进行api验证；根据api验证token方式来决定是否需要
            final String authenticate = authenticate();
            if (!TextUtils.isEmpty(authenticate)) {
                requestBuilder.addHeader("Authorization", "Bearer " + authenticate);
            }

            requestBuilder.addHeader("app_version", BuildConfig.VERSION_NAME);
            requestBuilder.addHeader("platform", "Android");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private String authenticate() {
        User currentUser = SharedPreferencesManager.getUser();
        if (currentUser != null) {
            return currentUser.getToken();
        }
        return null;
    }

}
