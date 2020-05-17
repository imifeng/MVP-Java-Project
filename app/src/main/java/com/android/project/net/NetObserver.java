package com.android.project.net;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @author Finn
 * @date 2020
 * 封装Observer，统一处理
 */
public abstract class NetObserver<T> implements Observer<T> {

    private Disposable disposable;

    public abstract void onSuccess(T response);

    public abstract void onFailure(Throwable e);

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e);
        if (disposable !=null && !disposable.isDisposed()){ //事件完成取消订阅
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (disposable !=null && !disposable.isDisposed()){ //事件完成取消订阅
            disposable.dispose();
        }
    }
}
