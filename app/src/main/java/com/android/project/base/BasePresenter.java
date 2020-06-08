package com.android.project.base;


import com.android.project.http.NetObserver;
import com.android.project.http.NetRequestListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Finn
 * @date 2020
 */
public class BasePresenter<V extends BaseView> {

    protected V mView;

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    public V getPView() {
        return mView;
    }


    /**
     * requestNetwork
     *
     * @param observable
     * @param listener
     * @param compose
     * @param <T>
     */

    <T> void requestNetwork(Observable<T> observable, NetRequestListener<T> listener, ObservableTransformer<T, T> compose) {
        observable.compose(compose)
                .to(getPView().bindAutoDispose()) //绑定 AutoDispose，跟随view生命周期
                .subscribe(new NetObserver<T>() {
                    @Override
                    public void onSuccess(T response) {
                        if (isViewAttached()) {
                            if (listener != null) {
                                listener.onSuccess(response);
                            }
                            getPView().hideLoading();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        if (isViewAttached()) {
                            if (listener != null) {
                                listener.onFailed(e);
                            }
                            getPView().hideLoading();
                        }
                    }
                });
    }


    protected <T> void requestNetwork(Observable<T> observable, NetRequestListener<T> observer) {
        requestNetwork(observable, observer, ioMainScheduler());
    }


    private <T> ObservableTransformer<T, T> ioMainScheduler() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

}
