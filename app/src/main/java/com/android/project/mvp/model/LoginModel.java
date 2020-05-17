package com.android.project.mvp.model;


import com.android.project.base.BaseModel;
import com.android.project.mvp.contract.LoginContract;
import com.android.project.mvp.model.bean.BaseResponse;
import com.android.project.mvp.model.bean.User;
import com.android.project.mvp.model.service.LoginService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Finn
 * @date 2020
 */
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Override
    public Observable<BaseResponse<User>> login(String username, String password) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password", password);

        return getRepository().create(LoginService.class).login(parameters);
    }
}
