package com.android.project.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.project.net.RxLifecycleUtils;
import com.android.project.utils.ToastUtils;
import com.android.project.manager.ProgressDialogManager;

import autodispose2.AutoDisposeConverter;

/**
 * @author Finn
 * @date 2020
 */
public abstract class BaseMvpFragment<V extends BaseView,P extends BasePresenter<V>>  extends BaseFragment implements BaseView{

    private P presenter;

    public P getPresenter() {
        return presenter;
    }

    public abstract P createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        if (getPresenter() != null) {
            getPresenter().attachView((V) this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroyView();
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

    @Override
    public void onError(Throwable throwable) {
        ToastUtils.showToast(throwable.getMessage());
    }

    @Override
    public void showLoading() {
        ProgressDialogManager.getInstance().showProgress(getActivity());
    }

    @Override
    public void hideLoading() {
        ProgressDialogManager.getInstance().dismissProgress();
    }
}
