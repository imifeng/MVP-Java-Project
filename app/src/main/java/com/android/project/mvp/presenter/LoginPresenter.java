package com.android.project.mvp.presenter;

import com.android.project.base.BasePresenter;
import com.android.project.mvp.model.bean.BaseResponse;
import com.android.project.mvp.model.bean.User;
import com.android.project.mvp.contract.LoginContract;
import com.android.project.mvp.model.LoginModel;
import com.android.project.net.NetRequestListener;

/**
 * @author Finn
 * @date 2020
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.Model model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void login(String username, String password) {
        if (!isViewAttached()) {
            return;
        }
        getPView().showLoading();
        requestNetwork(model.login(username, password), new NetRequestListener<BaseResponse<User>>() {
            @Override
            public void onSuccess(BaseResponse<User> response) {
                getPView().hideLoading();
                getPView().onLoginSuccess(response.getData());
            }

            @Override
            public void onFailed(Throwable e) {
                getPView().hideLoading();
                getPView().onError(e);
            }
        });
    }
}
