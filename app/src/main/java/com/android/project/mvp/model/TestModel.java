package com.android.project.mvp.model;


import com.android.project.base.BaseModel;
import com.android.project.mvp.contract.TestContract;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.mvp.model.service.TestService;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Finn
 * @date 2020
 */
public class TestModel extends BaseModel implements TestContract.Model {

    @Override
    public Observable<List<RepoBean>> getRepos(String username) {
        return getRepository().create(TestService.class).getRepos(username);
    }
}
