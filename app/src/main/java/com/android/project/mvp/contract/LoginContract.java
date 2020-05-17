package com.android.project.mvp.contract;


import com.android.project.base.BaseView;
import com.android.project.mvp.model.bean.BaseResponse;
import com.android.project.mvp.model.bean.User;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Finn
 * @date 2020
 */
public interface LoginContract {
    interface Model {
        Observable<BaseResponse<User>> login(String username, String password);
    }

    interface View extends BaseView {

        void onLoginSuccess(User bean);
    }

    interface Presenter {
        /**
         * 登陆
         *
         * @param username
         */
        void login(String username, String password);
    }
}
