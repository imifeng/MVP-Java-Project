package com.android.project.mvp.presenter;

import com.android.project.base.BasePresenter;
import com.android.project.mvp.contract.TestContract;
import com.android.project.mvp.model.TestModel;
import com.android.project.mvp.model.bean.RepoBean;
import com.android.project.net.NetRequestListener;

import java.util.List;

/**
 * @author Finn
 * @date 2020
 */
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    private TestContract.Model model;

    public TestPresenter() {
        model = new TestModel();
    }

    @Override
    public void getRepos(String username) {
        getPView().showLoading();
        requestNetwork(model.getRepos(username), new NetRequestListener<List<RepoBean>>() {
            @Override
            public void onSuccess(List<RepoBean> response) {
                getPView().onReposSuccess(response);
            }

            @Override
            public void onFailed(Throwable e) {
                getPView().onError(e);
            }
        });

    }


}
